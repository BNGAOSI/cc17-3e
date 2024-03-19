package com.ambiongltb.fulldashboardededdneddy;

import java.util.Map;

public class otherDocsRequests {
    String fullName, age, dateOfBirth, civilStatus, gender, address, Duration, purpose, inputDocumentType,documentType, otherDocsPhoneNumber;
    String status;
public String userTokenOtherDocs;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public otherDocsRequests(String status) {
        this.status = status;
    }

    Map time;

    public otherDocsRequests() {
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public otherDocsRequests(String fullName, String age, String dateOfBirth, String civilStatus, String gender, String address, String Duration, String inputDocumentType,String purpose, String documentType, Map time, String userTokenOtherDocs, String otherDocsPhoneNumber) {
        this.fullName = fullName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.civilStatus = civilStatus;
        this.gender = gender;
        this.address = address;
        this.Duration = Duration;
        this.inputDocumentType = inputDocumentType;
        this.purpose = purpose;
        this.documentType = documentType;
        this.time = time;
        this.userTokenOtherDocs = userTokenOtherDocs;
        this.otherDocsPhoneNumber = otherDocsPhoneNumber;

    }

    public String getInputDocumentType() {
        return inputDocumentType;
    }

    public void setInputDocumentType(String inputDocumentType) {
        this.inputDocumentType = inputDocumentType;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getOtherDocsPhoneNumber() {
        return otherDocsPhoneNumber;
    }

    public void setOtherDocsPhoneNumber(String otherDocsPhoneNumber) {
        this.otherDocsPhoneNumber = otherDocsPhoneNumber;
    }

    public String getUserTokenOtherDocs() {
        return userTokenOtherDocs;
    }

    public void setUserTokenOtherDocs(String userTokenOtherDocs) {
        this.userTokenOtherDocs = userTokenOtherDocs;
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

    public String getPresentAddress() {
        return address;
    }

    public void setPresentAddress(String address) {
        this.address = address;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Map getTime() {
        return time;
    }

    public void setTime(Map time) {
        this.time = time;
    }
}
