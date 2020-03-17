package com.ahmed.customapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.customapp.BaseActivity;
import com.ahmed.customapp.BaseFragment;
import com.ahmed.customapp.R;
import com.google.android.material.appbar.AppBarLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;

public class ProfileFragment extends BaseActivity {

    private AppBarLayout appBarLayout;
    private MotionLayout motionLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_fragment);

        initViews();

        coordinateMotion();
    }

    private void initViews(){
        appBarLayout = findViewById(R.id.appbar_layout);
        motionLayout = findViewById(R.id.motion_layout);
    }

    private void coordinateMotion(){
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float seekPosition = -verticalOffset / (float)appBarLayout.getTotalScrollRange();
                motionLayout.setProgress(seekPosition);

            }
        });
    }

}
