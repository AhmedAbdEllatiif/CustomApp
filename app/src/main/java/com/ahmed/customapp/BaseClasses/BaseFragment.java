package com.ahmed.customapp.BaseClasses;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected static final String AdMobID = "ca-app-pub-8926610076081506~2692840354";
    protected BaseActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;


    }

    public MaterialDialog showMessage(String title, String Message){
        return  activity.showMessage(title,Message);
    }

    public MaterialDialog showProgressBar(){
        return activity.showProgressBar("Please Wait", "Loading");
    }

    public void hideProgressBar(){

        activity.hideProgressBar();
    }


}