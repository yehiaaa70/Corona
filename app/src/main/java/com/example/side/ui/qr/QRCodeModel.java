package com.example.side.ui.qr;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QRCodeModel implements Serializable {

    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("numPositive")
    private int numPositive;
    @SerializedName("isClear")
    private boolean isClear;
    @SerializedName("user")
    private String user_id;


    public QRCodeModel( String name, String address, int numPositive, boolean isClear, String user_id) {
        this.name = name;
        this.address = address;
        this.numPositive = numPositive;
        this.isClear = isClear;
        this.user_id = user_id;
    }

    public QRCodeModel(int numPositive, boolean isClear) {
        this.numPositive = numPositive;
        this.isClear = isClear;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumPositive() {
        return numPositive;
    }

    public void setNumPositive(int numPositive) {
        this.numPositive = numPositive;
    }

    public boolean isClear() {
        return isClear;
    }

    public void setClear(boolean clear) {
        isClear = clear;
    }
}
