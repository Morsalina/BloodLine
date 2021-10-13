package com.example.bloodline;

public class GetSetUserInfo {

    private String fullname, contact, blood, location;

    public GetSetUserInfo(String fullname, String contact, String blood, String location) {
        this.fullname = fullname;
        this.contact = contact;
        this.blood = blood;
        this.location = location;
    }

    public GetSetUserInfo() {
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

