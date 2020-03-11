package com.ahmed.customapp.QuranKareem.QuranModels;

public class HadethModel {

    String title,content;

    public HadethModel() {
    }


    public HadethModel(String title, String content) {
        this.title = title;
        this.content = content;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}