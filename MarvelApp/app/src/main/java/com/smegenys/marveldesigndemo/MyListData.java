package com.smegenys.marveldesigndemo;


public class MyListData {
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    private String description;
    private int imgId;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    private int color;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String nickname;
    public MyListData(String description,String nickname, int imgId, int color) {
        this.description = description;
        this.imgId = imgId;
        this.color=color;
        this.nickname=nickname;
    }
}
