package com.example.fulldashboardededdneddy;

import java.util.Map;

public class BarangayClearanceRequests {

    Map time;
    String fullName, age, dateOfBirth, presentAddress, purpose, gender;

    public BarangayClearanceRequests() {
    }

    public BarangayClearanceRequests(Map time) {
        this.time = time;
    }

    public BarangayClearanceRequests(String fullName) {
        this.fullName = fullName;
    }



    public BarangayClearanceRequests(String fullName, String age, String dateOfBirth, String presentAddress, String purpose, String gender, Map time) {
        this.fullName = fullName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.presentAddress = presentAddress;
        this.purpose = purpose;
        this.gender = gender;
        this.time = time;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map getTime() {
        return time;
    }

    public void setTime(Map time) {
        this.time = time;
    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
