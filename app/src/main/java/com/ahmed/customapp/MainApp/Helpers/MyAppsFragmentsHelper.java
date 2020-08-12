package com.ahmed.customapp.MainApp.Helpers;

import com.ahmed.customapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyAppsFragmentsHelper {


    public static List<Integer> getQuranImageList() {
        List<Integer> imgList = new ArrayList<>();
        imgList.add(R.drawable.splash);
        imgList.add(R.drawable.quran0);
        imgList.add(R.drawable.quran1);
        imgList.add(R.drawable.quran2);
        imgList.add(R.drawable.quran3);

        return imgList;
    }


    public static List<Integer> getCalculatorImageList() {
        List<Integer> imgList = new ArrayList<>();
        imgList.add(R.drawable.calculator1);
        return imgList;
    }

}
