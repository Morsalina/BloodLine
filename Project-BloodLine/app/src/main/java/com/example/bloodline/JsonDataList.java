package com.example.bloodline;

public class JsonDataList {
    String fullname;
    String contact;
    String blood;
    String location;
    String status;
    String donationDate;
    String postCreationTime;
    String writtenPost;
    String bloodAmount;
    String hospitalName;
    String bloodStatus;

    public JsonDataList(String fullname, String contact, String blood, String location, String status) {
        this.fullname = fullname;
        this.contact = contact;
        this.blood = blood;
        this.location = location;
        this.status = status;
    }

    public JsonDataList(String donationDate){
        this.donationDate = donationDate;
    }

    public JsonDataList(String fullname, String contact, String postCreationTime, String writtenPost, String bloodAmount, String hospitalName){
        this.fullname = fullname;
        this.contact = contact;
        this.postCreationTime = postCreationTime;
        this.writtenPost = writtenPost;
        this.bloodAmount = bloodAmount;
        this.hospitalName = hospitalName;
    }

    public JsonDataList(String fullname, String contact, String postCreationTime, String writtenPost, String bloodAmount, String hospitalName, String bloodStatus){
        this.fullname = fullname;
        this.contact = contact;
        this.postCreationTime = postCreationTime;
        this.writtenPost = writtenPost;
        this.bloodAmount = bloodAmount;
        this.hospitalName = hospitalName;
        this.bloodStatus = bloodStatus;
    }

    public JsonDataList() {
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

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public String getPostCreationTime() {
        return postCreationTime;
    }

    public void setPostCreationTime(String postCreationTime) {
        this.postCreationTime = postCreationTime;
    }

    public String getWrittenPost() {
        return writtenPost;
    }

    public void setWrittenPost(String writtenPost) {
        this.writtenPost = writtenPost;
    }

    public String getBloodAmount() {
        return bloodAmount;
    }

    public void setBloodAmount(String bloodAmount) {
        this.bloodAmount = bloodAmount;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getBloodStatus() {
        return bloodStatus;
    }

    public void setBloodStatus(String bloodStatus) {
        this.bloodStatus = bloodStatus;
    }
}
