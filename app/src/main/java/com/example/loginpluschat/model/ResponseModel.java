package com.example.loginpluschat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseModel {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("age")
    @Expose
    private Integer age;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("user_id")
    @Expose
    private int userId;

    @SerializedName("message")
    @Expose
    private String message;

    public boolean isNewItemAdded() {
        return isNewItemAdded;
    }

    public void setNewItemAdded(boolean newItemAdded) {
        isNewItemAdded = newItemAdded;
    }

    private boolean isNewItemAdded;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}