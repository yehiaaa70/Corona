package com.example.side.ui.home;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class criticalModel implements Serializable {
    @SerializedName("_id")
    private String id;
    @SerializedName("user")
    private String username;
    @SerializedName("issue")
    private String description;
    @SerializedName("warning")
    private int warning;

    public criticalModel(String username, String description, int warning) {
        this.username = username;
        this.description = description;
        this.warning = warning;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWarning() {
        return warning;
    }

    public void setWarning(int warning) {
        this.warning = warning;
    }
}
