package com.ahmed.customapp.QuranKareem.QuranApi.Models;

import com.google.gson.annotations.SerializedName;

public class RadioItem {

    @SerializedName("Name")
    private String name;

    @SerializedName("URL")
    private String URL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}