package com.example.side.ui.volunteer;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class VolunteerModel implements Serializable {
    private String volunteerName;
    private String volunteerPhone;
    private int volunteerAge;
    private String volunteerAddress;
    private String volunteer_itemName_price;

    public VolunteerModel(String volunteerName, String volunteerPhone,
                          int volunteerAge, String volunteerAddress,
                          String volunteer_itemName_price) {
        this.volunteerName = volunteerName;
        this.volunteerPhone = volunteerPhone;
        this.volunteerAge = volunteerAge;
        this.volunteerAddress = volunteerAddress;
        this.volunteer_itemName_price = volunteer_itemName_price;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public String getVolunteerPhone() {
        return volunteerPhone;
    }

    public void setVolunteerPhone(String volunteerPhone) {
        this.volunteerPhone = volunteerPhone;
    }

    public int getVolunteerAge() {
        return volunteerAge;
    }

    public void setVolunteerAge(int volunteerAge) {
        this.volunteerAge = volunteerAge;
    }

    public String getVolunteerAddress() {
        return volunteerAddress;
    }

    public void setVolunteerAddress(String volunteerAddress) {
        this.volunteerAddress = volunteerAddress;
    }

    public String getVolunteer_itemName_price() {
        return volunteer_itemName_price;
    }

    public void setVolunteer_itemName_price(String volunteer_itemName_price) {
        this.volunteer_itemName_price = volunteer_itemName_price;
    }

    @Override
    public String toString() {
        return "VolunteerModel{" +
                "volunteerName='" + volunteerName + '\'' +
                ", volunteerPhone='" + volunteerPhone + '\'' +
                ", volunteerAge=" + volunteerAge +
                ", volunteerAddress='" + volunteerAddress + '\'' +
                ", volunteer_itemName_price='" + volunteer_itemName_price + '\'' +
                '}';
    }
}
