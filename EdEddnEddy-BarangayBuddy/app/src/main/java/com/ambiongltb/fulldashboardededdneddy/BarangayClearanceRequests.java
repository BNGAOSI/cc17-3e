package com.ambiongltb.fulldashboardededdneddy;

import java.util.Map;

public class BarangayClearanceRequests {

    Map time;
    String fullName, age, dateOfBirth, presentAddress, civilStatus, Duration, purpose, gender, documentType, phoneNumber;

    public String userTokenBarangayClearance;

    public BarangayClearanceRequests() {
    }

    public BarangayClearanceRequests(Map time) {
        this.time = time;
    }

    public BarangayClearanceRequests(String fullName) {
        this.fullName = fullName;
    }


    public BarangayClearanceRequests(String fullName, String age, String dateOfBirth, String presentAddress, String civilStatus, String Duration, String purpose, String gender, String documentType, Map time, String userTokenBarangayClearance, String phoneNumber) {
        this.fullName = fullName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.civilStatus = civilStatus;
        this.presentAddress = presentAddress;
        this.Duration = Duration;
        this.purpose = purpose;
        this.gender = gender;
        this.time = time;
        this.documentType = documentType;
        this.userTokenBarangayClearance = userTokenBarangayClearance;
        this.phoneNumber = phoneNumber;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserTokenBarangayClearance() {
        return userTokenBarangayClearance;
    }

    public void setUserTokenBarangayClearance(String userTokenBarangayClearance) {
        this.userTokenBarangayClearance = userTokenBarangayClearance;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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
