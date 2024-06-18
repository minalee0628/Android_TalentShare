package com.example.talentshare.ChatList;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talentshare.ChatList.EnterRoom.ChatActivity;
import com.example.talentshare.R;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    //--다이얼로그
    Dialog EenterDialog;
    Button btnNO;
    Button btnYes;



    //--데이터
    private ArrayList<ChatListData> arrayList;
    private Context context; // 어댑터에서 사용할 Context
    public ChatListAdapter(Context context, ArrayList<ChatListData> arrayList) {
        this.context = context; // 생성자에서 Context를 받아옴
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    //-- onCreateViewHolder 메소드
    public ChatListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_recyclerview_item, parent, false);
        ViewHolder holder = new ChatListAdapter.ViewHolder(view);

        //context = parent.getContext();    // 다이얼로그를 위한 context 받아오기
        /*dialog, toast는 context가 반드시 필요하나 fragment 나 adapter는 context가 없음.
         * 오로지 부모 activity에 존재하기 때문에 부모를 받을 수 있는 이곳에서 getContext()로 context를 구해오는 방법을 사용*/

        return holder;
    }

    //-- onBindViewHolder 메소드
    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.ViewHolder holder, int position){
        holder.roomName.setText(arrayList.get(position).getRoomName());
        holder.roomLimit.setText(arrayList.get(position).getRoomLimit());


        // 아이템 view를 클릭했을 때 -> 방으로 입장할지 물어봄
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatActivity.class);
                Log.i(this.getClass().getName(), "채팅방 리스트 클릭됨");        /// log
            }
        });

    }

    //--getItemCount 메소드
    public int getItemCount(){
        return arrayList.size();
    }


    //-- item 추가

    //-- item 삭제


    //-- 뷰홀더
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView roomName;
        TextView roomLimit;

        // 아이템 뷰 저장하는 뷰홀더
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.roomName = (TextView) itemView.findViewById(R.id.tv_room_name);
            this.roomLimit = (TextView) itemView.findViewById(R.id.tv_room_limit);
        }
    }

}
