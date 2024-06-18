package com.example.talentshare.MyPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.talentshare.Home.AddPost.AddPostActivity;
import com.example.talentshare.Login.LoginActivity;
import com.example.talentshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fragment_MyPage extends Fragment {
    //파이어베이스
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference userDatabaseRef;
    DatabaseReference postDatabaseRef;
    private String userId;
    private String userName;


    TextView user;
    Button btnLogout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        user = view.findViewById(R.id.tv_mypage_nickname);
        btnLogout = view.findViewById(R.id.btn_mypage_logout);



        //파이어베이스
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();


        //유저 uid 확인 후 닉네임 얻기
        if (currentUser != null) {
            userId = currentUser.getUid();
            userDatabaseRef = database.getReference("Users").child(userId);

            userDatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        userName = snapshot.child("nickname").getValue(String.class);
                        user.setText(userName);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //Toast.makeText(getActivity(), "유저 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                }
            });

        }


        //로그아웃 기능
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth.signOut();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish(); //현재 액티비티 종료
            }
        });


        return view;
    }
}
