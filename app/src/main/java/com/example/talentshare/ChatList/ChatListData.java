package com.example.talentshare.ChatList;

public class ChatListData {
    private String roomName;
    private String roomLimit;

    //--초기화
    public ChatListData(int roomImage, String roomName, String roomLimit){
        this.roomName = roomName;
        this.roomLimit = roomLimit;
    }


    //-- 채팅방 이름 관련
    // 채팅방 이름 불러오기
    public String getRoomName(){
        return roomName;
    }
    // 채팅방 이름 설정
    public void setRoomName(String roomName){
        this.roomName = roomName;
    }

    //-- 채팅방 인원
    // 채팅방 인원 불러오기
    public String getRoomLimit(){
        return roomLimit;
    }
    // 채팅방 인원 설정
    public void setRoomLimit(String roomLimit){
        this.roomLimit = roomLimit;
    }

}
