package com.example.lonse.entity;

public class AccountEntity {
    private int mPhoto;
    private String mName;
    private String mAccount;

    AccountEntity(int photo, String name,String account){
        this.mPhoto = photo;
        this.mName = name;
        this.mAccount = account;
    }
    public int getmPhoto(){
        return mPhoto;
    }
    public String getmName(){
        return mName;
    }
    public String getmMessage(){
        return mAccount;
    }
}
