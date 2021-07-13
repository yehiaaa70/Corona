package com.example.side.ui.home;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("name")
    private String Name;
    @SerializedName("phone")
    private int phone;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("role")
    private int role;

    public UserModel(String name, int phone, String email, String password, int role) {
        Name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
