package com.ahmed.customapp.MainApp.Models;

public class MyAppsModel {

    private int app_image;
    private String app_name;


    public MyAppsModel() {
    }


    public MyAppsModel(int app_image, String app_name) {
        this.app_image = app_image;
        this.app_name = app_name;
    }


    public int getApp_image() {
        return app_image;
    }

    public void setApp_image(int app_image) {
        this.app_image = app_image;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }
}
