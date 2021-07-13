package com.example.side.ui.patient;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class NurseModel implements Serializable {
    private String NurseName;
    private String NursePhone;
    private int NurseAge;
    private String NurseAddress;
    private String NursePrice;
    private String NurseBooking;
    private String NurseHospitalAddress;

    public NurseModel(String nurseName, String nursePhone,
                      int nurseAge, String nurseAddress,
                      String nursePrice, String nurseBooking,
                      String nurseHospitalAddress) {
        NurseName = nurseName;
        NursePhone = nursePhone;
        NurseAge = nurseAge;
        NurseAddress = nurseAddress;
        NursePrice = nursePrice;
        NurseBooking = nurseBooking;
        NurseHospitalAddress = nurseHospitalAddress;
    }

    public String getNurseName() {
        return NurseName;
    }

    public void setNurseName(String nurseName) {
        NurseName = nurseName;
    }

    public String getNursePhone() {
        return NursePhone;
    }

    public void setNursePhone(String nursePhone) {
        NursePhone = nursePhone;
    }

    public int getNurseAge() {
        return NurseAge;
    }

    public void setNurseAge(int nurseAge) {
        NurseAge = nurseAge;
    }

    public String getNurseAddress() {
        return NurseAddress;
    }

    public void setNurseAddress(String nurseAddress) {
        NurseAddress = nurseAddress;
    }

    public String getNursePrice() {
        return NursePrice;
    }

    public void setNursePrice(String nursePrice) {
        NursePrice = nursePrice;
    }

    public String getNurseBooking() {
        return NurseBooking;
    }

    public void setNurseBooking(String nurseBooking) {
        NurseBooking = nurseBooking;
    }

    public String getNurseHospitalAddress() {
        return NurseHospitalAddress;
    }

    public void setNurseHospitalAddress(String nurseHospitalAddress) {
        NurseHospitalAddress = nurseHospitalAddress;
    }
}
