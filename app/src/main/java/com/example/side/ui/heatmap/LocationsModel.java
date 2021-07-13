package com.example.side.ui.heatmap;

public class LocationsModel {
    private double Latitude , Longitude;
    private String User_id;

    public LocationsModel(double latitude, double longitude, String user_id) {
        Latitude = latitude;
        Longitude = longitude;
        User_id = user_id;
    }

    public LocationsModel() {
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    @Override
    public String toString() {
        return "LocationsModel{" +
                "Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", User_id='" + User_id + '\'' +
                '}';
    }
}
