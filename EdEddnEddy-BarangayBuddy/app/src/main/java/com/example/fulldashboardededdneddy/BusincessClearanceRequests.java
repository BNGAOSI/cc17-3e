package com.example.fulldashboardededdneddy;

import java.util.Map;

public class BusincessClearanceRequests {
    String fullname, nameOfBussiness, dateIssued, businessAddress;
    Map time;

    public BusincessClearanceRequests() {
    }

    public BusincessClearanceRequests(String fullname, String nameOfBussiness, String dateIssued, String businessAddress, Map time) {
        this.fullname = fullname;
        this.nameOfBussiness = nameOfBussiness;
        this.dateIssued = dateIssued;
        this.businessAddress = businessAddress;
        this.time = time;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNameOfBussiness() {
        return nameOfBussiness;
    }

    public void setNameOfBussiness(String nameOfBussiness) {
        this.nameOfBussiness = nameOfBussiness;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
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
