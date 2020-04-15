package com.ahmed.customapp.BaseClasses;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ahmed.customapp.R;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected static final String AdMobID = "ca-app-pub-8926610076081506~2692840354";
    protected BaseActivity activity;
    protected static final int REQUEST_CALL = 35;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;


    }

    protected boolean isPhonePermissionGranted(){
      if ( (activity.checkSelfPermission(
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)){
          requestCallPermission();
          return false;
      }
      return true;
    }

    private void requestCallPermission(){
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
    }

}