package com.example.talentshare.ChatList;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talentshare.R;

import java.util.ArrayList;

public class Fragment_ChatList extends Fragment {

    private ArrayList<ChatListData> arrayList;
    private ChatListAdapter chatListAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    Button makeBtn;

    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        //-- 리사이클러뷰
        // 리사이클러뷰에 linearLayoutManager 지정해주기
        recyclerView = view.findViewById(R.id.rv_chatList);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        // 리사이클러뷰에 Adapter 지정해주기
        chatListAdapter = new ChatListAdapter(container.getContext(), arrayList);
        recyclerView.setAdapter(chatListAdapter);



        return view;
    }

}