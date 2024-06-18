package com.example.talentshare.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.talentshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SigninActivity extends AppCompatActivity {
    //파이어베이스
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference reference;


    private Button btnMakeAccount;
    private Button btnGoBack;
    private EditText et_signin_email;
    private EditText et_signin_password;
    private EditText et_signin_nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin1);

        //파이어베이스
        firebaseAuth = firebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");
        //
        btnMakeAccount = findViewById(R.id.btn_signin_ok);
        btnGoBack = findViewById(R.id.btn_signin_goback);
        et_signin_email = findViewById(R.id.et_signin_email);
        et_signin_password = findViewById(R.id.et_signin_password);
        et_signin_nickName = findViewById(R.id.et_signin_nickName);


        //계정 생성
        btnMakeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_signin_email.getText().toString().trim();
                String password = et_signin_password.getText().toString().trim();
                String name = et_signin_nickName.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                    Toast.makeText(SigninActivity.this, "내용을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isComplete()){
                                        //계정 생성 성공시
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        String email = user.getEmail();
                                        String userid = user.getUid();
                                        String nickname = name;

                                        //해쉬맵 테이블 사용해서 데이터 베이스에 유저 정보 저장
                                        HashMap<Object,String> hashMap = new HashMap<>();
                                        hashMap.put("userid",userid);
                                        hashMap.put("email",email);
                                        hashMap.put("nickname",name);

                                        reference.child(userid).setValue(hashMap);

                                        Toast.makeText(SigninActivity.this, "계정생성 완료", Toast.LENGTH_SHORT).show();


                                        //로그인 화면으로 이동
                                        Intent intent = new Intent(SigninActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(SigninActivity.this, "계정생성 오류", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });


        //뒤로가기 버튼
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그인 화면으로 이동
                Intent intent = new Intent(SigninActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
