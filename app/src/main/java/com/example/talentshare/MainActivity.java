package com.example.talentshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.talentshare.ChatList.Fragment_ChatList;
import com.example.talentshare.Home.Fragment_Home;
import com.example.talentshare.MyPage.Fragment_MyPage;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    // 프래그먼트 객체 선언
    Fragment fragment_ChatList;
    Fragment fragment_Home;
    Fragment fragment_MyPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Talent Share");


        // 프래그먼트 객체 생성
        fragment_ChatList = new Fragment_ChatList();
        fragment_Home = new Fragment_Home();
        fragment_MyPage = new Fragment_MyPage();



        // 처음 뷰 선택
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment_Home).commitAllowingStateLoss();

        // 하단 메뉴 객체 선언
        NavigationBarView bottomMenu = findViewById(R.id.bottom_navigation);

        // 메뉴 선택 클릭 리스너
        bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                int itemId = item.getItemId();
                if (itemId == R.id.fragment_one) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment_Home).commit();
                    Log.i(this.getClass().getName(), "하단 메뉴에서 채팅방 아이콘 클릭");        /// log
                    return true;
                } else if (itemId == R.id.fragment_three) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment_MyPage).commit();
                    Log.i(this.getClass().getName(), "하단 메뉴에서 마이페이지 아이콘 클릭");        /// log
                    return true;
                }
                return false;
            }

        });
    }
}