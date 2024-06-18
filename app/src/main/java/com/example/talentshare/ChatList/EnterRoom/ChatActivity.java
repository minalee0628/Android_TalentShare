package com.example.talentshare.ChatList.EnterRoom;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talentshare.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerViewChatMessages;
    private EditText editTextMessage;
    private Button buttonSend;
    private ArrayList<MessageData> messageList;
    private DatabaseReference chatDatabaseRef;
    private String currentUserId;
    private String chatRoomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatRoomId = getIntent().getStringExtra("chatRoomId");

        chatDatabaseRef = FirebaseDatabase.getInstance().getReference("Chats").child(chatRoomId);
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    }
}
