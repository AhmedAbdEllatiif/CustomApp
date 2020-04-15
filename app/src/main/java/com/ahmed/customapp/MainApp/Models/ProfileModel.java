package com.ahmed.customapp.MainApp.Models;

public class ProfileModel {

    private int name;
    private int email;
    private int phoneNumber;
    private int gender;
    private int nationality;
    private int maritalStatus;
    private int militaryStatus;
    private int pdfName;


    public ProfileModel() { }

    public ProfileModel(int name, int email, int phoneNumber, int gender, int nationality, int maritalStatus, int militaryStatus, int pdfName) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.nationality = nationality;
        this.maritalStatus = maritalStatus;
        this.militaryStatus = militaryStatus;
        this.pdfName = pdfName;
    }


    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getNationality() {
        return nationality;
    }

    public void setNationality(int nationality) {
        this.nationality = nationality;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public int getMilitaryStatus() {
        return militaryStatus;
    }

    public void setMilitaryStatus(int militaryStatus) {
        this.militaryStatus = militaryStatus;
    }

    public int getPdfName() {
        return pdfName;
    }

    public void setPdfName(int pdfName) {
        this.pdfName = pdfName;
    }
}
