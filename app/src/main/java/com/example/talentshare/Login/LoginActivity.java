package com.example.talentshare.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.talentshare.MainActivity;
import com.example.talentshare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    //파이어베이스
    FirebaseAuth firebaseAuth;

    //
    EditText inputEmail;
    EditText inputPassword;
    private Button btnLogIn;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //파이어베이스
        firebaseAuth =  FirebaseAuth.getInstance();

        // 객체 설정
        inputEmail = findViewById(R.id.et_login_id);
        inputPassword = findViewById(R.id.et_login_password);
        btnLogIn = findViewById(R.id.btn_login);
        btnSignIn = findViewById(R.id.btn_signin);

        //로그인 버튼 클릭시 mainActivity로 이동
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그인 성공시
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "이메일과 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){    //등록된 아이디일 경우
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "없는 아이디입니다", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });

        //회원가입 버튼 클릭시 mainActivity로 이동
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });


    }
}