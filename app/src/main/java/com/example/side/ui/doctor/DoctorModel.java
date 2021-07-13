package com.example.side.ui.doctor;


import androidx.annotation.Keep;

import java.io.Serializable;

@Keep public class DoctorModel implements Serializable {
    String name;
    String phone;
    int age;
    String address;
    String price;
    String booking;
    String clincksAddress;
    String certifications;

    public DoctorModel(String name,
                       String phone,
                       int age,
                       String address,
                       String price,
                       String booking,
                       String clincksAddress,
                       String certifications) {

        this.name = name;
        this.phone = phone;
        this.age = age;
        this.address = address;
        this.price = price;
        this.booking = booking;
        this.clincksAddress = clincksAddress;
        this.certifications = certifications;
    }

    public DoctorModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public String getClincksAddress() {
        return clincksAddress;
    }

    public void setClincksAddress(String clincksAddress) {
        this.clincksAddress = clincksAddress;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    @Override
    public String toString() {
        return "DoctorModel{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", price='" + price + '\'' +
                ", booking='" + booking + '\'' +
                ", clincksAddress='" + clincksAddress + '\'' +
                ", certifications='" + certifications + '\'' +
                '}';
    }
}
