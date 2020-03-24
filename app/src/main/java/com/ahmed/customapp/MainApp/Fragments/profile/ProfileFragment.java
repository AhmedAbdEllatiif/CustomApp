package com.ahmed.customapp.MainApp.Fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ahmed.customapp.R;
import com.google.android.material.appbar.AppBarLayout;

public class ProfileFragment extends Fragment {

    private View view;

    private ProfileViewModel profileViewModel;

    private AppBarLayout appBarLayout;
    private MotionLayout motionLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        view = inflater.inflate(R.layout.profile_fragment, container, false);

        initViews();

        coordinateMotion();

        return view;
    }



    private void initViews(){
        appBarLayout = view.findViewById(R.id.appbar_layout);
        motionLayout = view.findViewById(R.id.motion_layout);
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
