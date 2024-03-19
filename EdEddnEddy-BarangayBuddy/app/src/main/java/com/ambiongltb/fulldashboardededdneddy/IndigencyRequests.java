package com.ambiongltb.fulldashboardededdneddy;

import java.util.Map;

public class IndigencyRequests {

    String fullName, age, dateOfBirth, civilStatus, gender, address, Duration, documentType, indigencyPhoneNumber;
    String status;
    public String userTokenIndigency;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public IndigencyRequests(String status) {
        this.status = status;
    }

    Map time;

    public IndigencyRequests() {
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public IndigencyRequests(String fullName, String age, String dateOfBirth, String civilStatus, String gender, String address, String Duration, String documentType, Map time, String userTokenIndigency, String indigencyPhoneNumber) {
        this.fullName = fullName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.civilStatus = civilStatus;
        this.gender = gender;
        this.address = address;
        this.Duration = Duration;
        this.documentType = documentType;
        this.time = time;
        this.userTokenIndigency = userTokenIndigency;
        this.indigencyPhoneNumber = indigencyPhoneNumber;

    }

    public String getindigencyPhoneNumber() {
        return indigencyPhoneNumber;
    }

    public void setindigencyPhoneNumber(String indigencyPhoneNumber) {
        this.indigencyPhoneNumber = indigencyPhoneNumber;
    }

    public String getuserTokenIndigency() {
        return userTokenIndigency;
    }

    public void setuserTokenIndigency(String userTokenIndigency) {
        this.userTokenIndigency = userTokenIndigency;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public Map getTime() {
        return time;
    }

    public void setTime(Map time) {
        this.time = time;
    }

}
