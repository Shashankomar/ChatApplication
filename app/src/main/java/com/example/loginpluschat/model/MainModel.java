package com.example.loginpluschat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainModel {
    @SerializedName("success")
    @Expose
    private int success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("users")
    @Expose
    private List<ResponseModel> users = null;

    @SerializedName("user")
    @Expose
    private ResponseModel user;

    @SerializedName("chats")
    @Expose
    private List<ResponseModel> chats = null;

    @SerializedName("chat")
    @Expose
    private ResponseModel chat;

    public String getMessage() {
        return message;
    }

    public List<ResponseModel> getUsers() {
        return users;
    }

    public ResponseModel getUser() {
        return user;
    }

    public List<ResponseModel> getChats() {
        return chats;
    }

    public ResponseModel getChat() {
        return chat;
    }
}
