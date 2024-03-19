package com.ambiongltb.fulldashboardededdneddy;

import java.util.Map;

public class BusincessClearanceRequests {
    String fullname;
    String age, dateOfBirth;
    String civilStatus;

    String gender;
    String Duration;

    String nameOfBussiness;
    String typeOfBusiness;
    String businessAddress;
    String documentType;
    Map time;
    public String userTokenBusiness;

    String businessClearancePhone;

    public BusincessClearanceRequests() {
    }

    public BusincessClearanceRequests(String fullname, String dateOfBirth, String age,String civilStatus, String Duration, String gender, String nameOfBussiness, String typeOfBusiness, String businessAddress, String documentType, Map time, String userTokenBusiness, String businessClearancePhone) {
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.civilStatus = civilStatus;
        this.Duration = Duration;
        this.gender = gender;
        this.nameOfBussiness = nameOfBussiness;
        this.typeOfBusiness = typeOfBusiness;
        this.businessAddress = businessAddress;
        this.time = time;
        this.documentType = documentType;
        this.userTokenBusiness = userTokenBusiness;
        this.businessClearancePhone = businessClearancePhone;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getBusinessClearancePhone() {
        return businessClearancePhone;
    }

    public void setBusinessClearancePhone(String businessClearancePhone) {
        this.businessClearancePhone = businessClearancePhone;
    }

    public String getUserTokenBusiness() {
        return userTokenBusiness;
    }

    public void setUserTokenBusiness(String userTokenBusiness) {
        this.userTokenBusiness = userTokenBusiness;
    }

    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }

    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public String getNameOfBussiness() {
        return nameOfBussiness;
    }

    public void setNameOfBussiness(String nameOfBussiness) {
        this.nameOfBussiness = nameOfBussiness;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public Map getTime() {
        return time;
    }

    public void setTime(Map time) {
        this.time = time;
    }
}
