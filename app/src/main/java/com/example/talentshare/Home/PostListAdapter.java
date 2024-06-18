package com.example.talentshare.Home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.talentshare.ChatList.ChatListAdapter;
import com.example.talentshare.ChatList.ChatListData;
import com.example.talentshare.Home.LookPost.LookPostActivity;
import com.example.talentshare.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    //--다이얼로그
    Dialog EenterDialog;
    Button btnNO;
    Button btnYes;

    String postId;

    //--데이터
    private ArrayList<PostListData> arrayList;
    private Context context; // 어댑터에서 사용할 Context
    public PostListAdapter(Context context, ArrayList<PostListData> arrayList) {
        this.context = context; // 생성자에서 Context를 받아옴
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    //-- onCreateViewHolder 메소드
    public PostListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_recylerview_item, parent, false);
        PostListAdapter.ViewHolder holder = new PostListAdapter.ViewHolder(view);

        //context = parent.getContext();    // 다이얼로그를 위한 context 받아오기
        /*dialog, toast는 context가 반드시 필요하나 fragment 나 adapter는 context가 없음.
         * 오로지 부모 activity에 존재하기 때문에 부모를 받을 수 있는 이곳에서 getContext()로 context를 구해오는 방법을 사용*/

        return holder;
    }

    //-- onBindViewHolder 메소드
    @Override
    public void onBindViewHolder(@NonNull PostListAdapter.ViewHolder holder, int position){
        holder.postName.setText(arrayList.get(position).getTitle()); // 게시글 제목
        holder.postUser.setText(arrayList.get(position).getUserNickname()); // 작성자 닉네임
        holder.postDate.setText(arrayList.get(position).getDate()); // 작성 시간
        postId = arrayList.get(position).getPostId(); //게시물 id


        // 아이템 view를 클릭했을 때 -> 입장
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), LookPostActivity.class);
                intent.putExtra("postId",arrayList.get(position).getPostId());
                view.getContext().startActivity(intent);
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
        TextView postName;
        TextView postDate;
        TextView postUser;
        String postId;
        // 아이템 뷰 저장하는 뷰홀더
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.postName = (TextView) itemView.findViewById(R.id.tv_post_name);
            this.postUser = (TextView) itemView.findViewById(R.id.tv_post_user_name);
            this.postDate = (TextView) itemView.findViewById(R.id.tv_post_date);
        }
    }

    private String Timestampformat(long timestamp) {
        SimpleDateFormat sampledata = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
        return sampledata.format(new Date(timestamp));
    }
}
