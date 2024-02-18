package com.example.fulldashboardededdneddy;

import java.util.Map;

public class residencyrequests {
    String fullName, age, dateOfBirth, civilStatus, gender, address, duration, documentType, residencyPhoneNumber;
    String status;
    public String userTokenResidency;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public residencyrequests(String status) {
        this.status = status;
    }

    Map time;

    public residencyrequests() {
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public residencyrequests(String fullName, String age, String dateOfBirth, String civilStatus, String gender, String address, String duration, String documentType, Map time, String userTokenResidency, String residencyPhoneNumber) {
        this.fullName = fullName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.civilStatus = civilStatus;
        this.gender = gender;
        this.address = address;
        this.duration = duration;
        this.documentType = documentType;
        this.time = time;
        this.userTokenResidency = userTokenResidency;
        this.residencyPhoneNumber = residencyPhoneNumber;

    }

    public String getResidencyPhoneNumber() {
        return residencyPhoneNumber;
    }

    public void setResidencyPhoneNumber(String residencyPhoneNumber) {
        this.residencyPhoneNumber = residencyPhoneNumber;
    }

    public String getUserTokenResidency() {
        return userTokenResidency;
    }

    public void setUserTokenResidency(String userTokenResidency) {
        this.userTokenResidency = userTokenResidency;
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
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Map getTime() {
        return time;
    }

    public void setTime(Map time) {
        this.time = time;
    }
}
