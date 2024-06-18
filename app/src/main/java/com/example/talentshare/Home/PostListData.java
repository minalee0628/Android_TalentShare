package com.example.talentshare.Home;

public class PostListData {
    private String postId;
    private String title;
    private String content;
    private String userId;
    private String userNickname;
    private String date;

    //기본 생성자
    public PostListData() {}

    //--초기화
    public PostListData(String postId, String title, String content, String userId, String userNickname, String date){
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.userNickname = userNickname;
        this.date = date;
    }



    //-- 게시물 아이디
    // 게시물 아이디 불러오기
    public String getPostId() {
        return postId;
    }
    // 게시물 아이디 설정
    public void setPostId(String postId){
        this.postId = postId;
    }

    //-- 게시물 이름
    // 게시물 이름 불러오기
    public String getTitle() {return title;}

    // 게시물 이름 설정
    public void setTitle(String title) {this.title = title;}



    //-- 게시물 작성자 닉네임
    // 게시물 작성자 닉네임 불러오기
    public String getContent() {return content;}
    // 게시물 작성자 설정
    public void setContent(String content) {this.content = content;}

    //-- 게시물 내용
    // 게시물 내용 불러오기
    public String getUserId() {
        return userId;
    }
    // 게시물 내용 설정
    public void setUserId(String userId) {
        this.userId = userId;
    }

    //-- 게시물 작성자 닉네임
    // 게시물 작성자 닉네임 불러오기
    public String getUserNickname() {
        return userNickname;
    }
    // 게시물 작성자 설정
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }


    //-- 게시물 날짜
    // 채팅방 인원 불러오기
    public String getDate() {
        return date;
    }
    // 채팅방 인원 설정
    public void setDate(String date) {
        this.date = date;
    }


}
