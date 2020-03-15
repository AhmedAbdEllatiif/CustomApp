package com.ahmed.customapp;

public class CardItem {
    private int mTextResource;
    private int mTitleResource;
    private int image;

    public CardItem(int mTextResource, int mTitleResource, int image) {
        this.mTextResource = mTextResource;
        this.mTitleResource = mTitleResource;
        this.image = image;
    }

    public int getText() {
        return mTextResource;
    }

    public int getTitle() {
        return mTitleResource;
    }

    public int getImage() {
        return image;
    }



}
