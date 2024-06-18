package com.example.talentshare.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talentshare.ChatList.ChatListAdapter;
import com.example.talentshare.ChatList.ChatListData;
import com.example.talentshare.Home.AddPost.AddPostActivity;
import com.example.talentshare.MainActivity;
import com.example.talentshare.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Fragment_Home extends Fragment {
    //파이어베이스
    private DatabaseReference postDatabaseRef;
    private FirebaseDatabase database;
    //객체
    private ArrayList<PostListData> arrayList;
    private PostListAdapter postListAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        //-- 리사이클러뷰
        // 리사이클러뷰에 linearLayoutManager 지정해주기
        recyclerView = view.findViewById(R.id.rv_postList);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        // 리사이클러뷰에 Adapter 지정해주기
        postListAdapter = new PostListAdapter(container.getContext(), arrayList);
        recyclerView.setAdapter(postListAdapter);

        //---
        // 게시물 데이터 파이어베이스 가져오기
        database = FirebaseDatabase.getInstance();
        postDatabaseRef = database.getReference("Posts");

        postDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    String postId = postSnapshot.getKey();
                    String title = postSnapshot.child("title").getValue(String.class);
                    String content = postSnapshot.child("content").getValue(String.class);
                    String userId = postSnapshot.child("userId").getValue(String.class);
                    String userNickname = postSnapshot.child("userNickname").getValue(String.class);
                    String date = postSnapshot.child("date").getValue(String.class);


                    PostListData post = new PostListData(postId, title, content, userId, userNickname, date);
                    arrayList.add(0,post);
                }
                postListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "게시물 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
            }
        });

        //---
        //-- 리사이클러뷰의 item 추가
        Button makeBtn = view.findViewById(R.id.btn_make_Post);
        makeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PostListData postListData = new PostListData("새로운 게시물","게시물 작성자","날짜");
                //arrayList.add(postListData);
                //postListAdapter.notifyDataSetChanged();
                // 게시글 정보 저장
                Intent intent = new Intent(getActivity(), AddPostActivity.class);
                startActivity(intent);

                Log.i(this.getClass().getName(), "채팅방 생성 버튼 클릭함");        /// log
            }
        });



        return view;
    }
}
