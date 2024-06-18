package com.example.talentshare.ChatList.EnterRoom;

public class MessageData {
    String name;
    String message;
    String time;

    public MessageData(String name, String message, String time) {
        this.name = name;
        this.message = message;
        this.time = time;
    }

    public MessageData() {
    }

    //Getter & Setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

}
