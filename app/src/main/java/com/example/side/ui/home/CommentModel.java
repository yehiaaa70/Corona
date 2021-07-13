package com.example.side.ui.home;

import com.google.gson.annotations.SerializedName;

public class CommentModel {

    @SerializedName("post")
    private String postId;
    @SerializedName("user")
    private String username;
    @SerializedName("comment")
    private String comment;

    public CommentModel(String postId, String username, String comment) {
        this.postId = postId;
        this.username = username;
        this.comment = comment;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
