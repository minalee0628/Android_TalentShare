package com.example.talentshare.Home.AddPost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.talentshare.Login.SigninActivity;
import com.example.talentshare.MainActivity;
import com.example.talentshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.A;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddPostActivity extends AppCompatActivity {

    EditText postTitle;
    TextView postUser;
    EditText postContent;
    Button btnUpload;

    //파이어베이스
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference userDatabaseRef;
    DatabaseReference postDatabaseRef;
    private String userId;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        //파이어베이스
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        // 객체
        postTitle = findViewById(R.id.et_addpost_Title);
        postUser = findViewById(R.id.et_addpost_userName);
        postContent = findViewById(R.id.et_addpost_content);

        btnUpload = findViewById(R.id.btn_addpost_upload);



        //유저 uid 확인 후 닉네임 얻기
        if (currentUser != null) {
            userId = currentUser.getUid();
            userDatabaseRef = database.getReference("Users").child(userId);

            userDatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        userName = snapshot.child("nickname").getValue(String.class);
                        postUser.setText(userName);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AddPostActivity.this, "작성자 넥네임 확인 오류", Toast.LENGTH_SHORT).show();
                }

            });
        }



        //버튼 클릭시 게시&정보 저장
        postDatabaseRef = database.getReference("Posts");
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePostData();
            }
        });

    }


    //게시물 데이터 저장 함수
    private void savePostData(){
        String title = postTitle.getText().toString().trim();
        String content = postContent.getText().toString().trim();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(AddPostActivity.this, "모두 작성해주세요.", Toast.LENGTH_SHORT).show();
        }
        else {
            // 게시글 정보 저장
            //게시물 id
            String postId = postDatabaseRef.push().getKey();
            //게시물 작성 시간
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat dateform = new SimpleDateFormat("MM-dd hh:mm");
            String postTime = dateform.format(date);

            //정보 저장
            HashMap<String, String> postMap = new HashMap<>();
            postMap.put("postId", postId);
            postMap.put("title", title);
            postMap.put("content", content);
            postMap.put("userId", userId);
            postMap.put("userNickname", userName);
            postMap.put("date", postTime);

            postDatabaseRef.child(postId).setValue(postMap);
            Toast.makeText(AddPostActivity.this, "게시물 게시 완료", Toast.LENGTH_SHORT);


            //자유게시판으로 다시 이동
            Intent intent = new Intent(AddPostActivity.this, MainActivity.class);
            startActivity(intent);
            finish();    
        }
        
    }

}
