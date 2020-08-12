package com.ahmed.customapp.MainApp.Fragments.profile;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmed.customapp.MainApp.Models.ProfileModel;
import com.ahmed.customapp.R;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";
    private MutableLiveData<ProfileModel> profileLiveData;

    public ProfileViewModel() {
        profileLiveData = new MutableLiveData<>();
        setProfileLiveData();
    }

    public LiveData<ProfileModel> getProfileData() {
        return profileLiveData;
    }


    public void setProfileLiveData(){
        profileLiveData.setValue(getProfileModel());
    }

    public void test_setProfileLiveData() {
        Log.e(TAG, "test_setProfileLiveData" );
        profileLiveData.setValue(new ProfileModel(R.string.about,
                R.string.basic_calculator,
                R.string.completed,
                R.string.call,
                R.string.nationality,
                R.string.marital_status,
                R.string.military_status,
                R.string.cv_name));
    }

    public void postProfileLiveData(){
        Log.e(TAG, "postProfileLiveData");
        profileLiveData.setValue(new ProfileModel(R.string.my_name,
                R.string.my_email,
                R.string.my_num,
                R.string.gender,
                R.string.nationality,
                R.string.marital_status,
                R.string.military_status,
                R.string.cv_name));
    }

    private ProfileModel getProfileModel(){
     return    new ProfileModel(R.string.my_name,
                R.string.cv_name,
                R.string.clr,
                R.string.app_name,
                R.string.nationality,
                R.string.marital_status,
                R.string.military_status,
                R.string.cv_name);
    }


    public Intent makeCall(String number){
        Intent intent = new Intent(Intent.ACTION_CALL);

        intent.setData(Uri.parse("tel:" + number));
       return intent;
    }


}