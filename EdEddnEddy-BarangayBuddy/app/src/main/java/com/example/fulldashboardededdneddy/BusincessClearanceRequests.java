package com.example.fulldashboardededdneddy;

import java.util.Map;

public class BusincessClearanceRequests {
    String fullname;
    String nameOfBussiness;
    String typeOfBusiness;
    String businessAddress;
    String documentType;
    Map time;
    public String userTokenBusiness;

    String businessClearancePhone;

    public BusincessClearanceRequests() {
    }

    public BusincessClearanceRequests(String fullname, String nameOfBussiness, String typeOfBusiness, String businessAddress, String documentType, Map time, String userTokenBusiness, String businessClearancePhone) {
        this.fullname = fullname;
        this.nameOfBussiness = nameOfBussiness;
        this.typeOfBusiness = typeOfBusiness;
        this.businessAddress = businessAddress;
        this.time = time;
        this.documentType = documentType;
        this.userTokenBusiness = userTokenBusiness;
        this.businessClearancePhone = businessClearancePhone;
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
