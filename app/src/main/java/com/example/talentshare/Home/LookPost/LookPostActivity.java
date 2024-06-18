package com.example.talentshare.Home.LookPost;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talentshare.Home.PostListAdapter;
import com.example.talentshare.Home.PostListData;
import com.example.talentshare.Login.LoginActivity;
import com.example.talentshare.MainActivity;
import com.example.talentshare.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LookPostActivity extends AppCompatActivity {
    //파이어베이스
    private DatabaseReference postDatabaseRef;
    private FirebaseDatabase database;
    //객체
    TextView userName;
    TextView postContent;
    TextView postTitle;
    Button btnMsg;
    ImageView btnBack;
    Context context;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_post);


        //객체
        userName = findViewById(R.id.tv_lookpost_userName);
        postContent = findViewById(R.id.tv_lookpost_content);
        postTitle = findViewById(R.id.tv_lookpost_Title);
        btnBack = findViewById(R.id.btn_lookpost_back);

        //intent에서 postid 정보 받기
        Intent getIntent = getIntent();
        String postId = getIntent.getStringExtra("postId");


        // 게시물 데이터 파이어베이스 가져오기
        database = FirebaseDatabase.getInstance();
        postDatabaseRef = database.getReference("Posts").child(postId);

        postDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    PostListData post = snapshot.getValue(PostListData.class);
                    if (post != null) {
                        String postId = post.getPostId();
                        postTitle.setText( post.getTitle());
                        postContent.setText(post.getContent());
                        String userId = post.getUserId();
                        userName.setText(post.getUserNickname());
                        String date = post.getDate();
                    }


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LookPostActivity.this, "게시물 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
            }
        });

        //뒤로 가기 버튼
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /*
        * //메시지 버튼 클릭
        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LookPostActivity.this, MainActivity.class);
                intent.putExtra("chatRoomId", userId);
                startActivity(intent);
            }
        });*/

    }

}
