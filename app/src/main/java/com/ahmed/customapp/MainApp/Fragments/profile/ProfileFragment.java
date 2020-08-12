package com.ahmed.customapp.MainApp.Fragments.profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ahmed.customapp.BaseClasses.BaseFragment;
import com.ahmed.customapp.MainApp.Helpers.OpenSocialMediaHelper;
import com.ahmed.customapp.MainApp.Models.ProfileModel;
import com.ahmed.customapp.R;
import com.google.android.material.appbar.AppBarLayout;

import static com.ahmed.customapp.MainApp.Helpers.OpenSocialMediaHelper.FB_PACKAGE_NAME;
import static com.ahmed.customapp.MainApp.Helpers.OpenSocialMediaHelper.FB_URL_Browser;
import static com.ahmed.customapp.MainApp.Helpers.OpenSocialMediaHelper.GITHUB_URL;
import static com.ahmed.customapp.MainApp.Helpers.OpenSocialMediaHelper.INSTAGRAM_PACKAGE_NAME;
import static com.ahmed.customapp.MainApp.Helpers.OpenSocialMediaHelper.LINKEDIN_PACKAGE_NAME;
import static com.ahmed.customapp.MainApp.Helpers.OpenSocialMediaHelper.LINKEDIN_URL;

public class ProfileFragment extends BaseFragment {

    private View view;

    private ProfileViewModel viewModel;

    private AppBarLayout appBarLayout;
    private MotionLayout motionLayout;

    private ImageView img_fb;
    private ImageView img_whatsapp;
    private ImageView img_instagram;
    private ImageView img_linkedin;
    private ImageView img_github;


    private TextView txt_call;

    private TextView txt_name;
    private TextView txt_email;
    private TextView txt_gender_var;
    private TextView txt_nationality_var;
    private TextView txt_marital_status_var;
    private TextView txt_military_status_var;



    private OpenSocialMediaHelper helper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        view = inflater.inflate(R.layout.profile_fragment, container, false);

        helper = new OpenSocialMediaHelper(getContext());

        initViews();

        coordinateMotion();

        onViewsClicked();

        fillViewsWithProfileData();

        return view;
    }


    private void initViews() {
        appBarLayout = view.findViewById(R.id.appbar_layout);
        motionLayout = view.findViewById(R.id.motion_layout);

        img_fb = view.findViewById(R.id.img_fb);
        img_whatsapp = view.findViewById(R.id.img_whatsapp);
        img_instagram = view.findViewById(R.id.img_instagram);
        img_linkedin = view.findViewById(R.id.img_linkedin);
        img_github = view.findViewById(R.id.img_github);


        txt_call = view.findViewById(R.id.txt_call);

        txt_name = view.findViewById(R.id.txt_name);
        txt_email = view.findViewById(R.id.txt_email);
        txt_gender_var = view.findViewById(R.id.txt_gender_var);
        txt_nationality_var = view.findViewById(R.id.txt_nationality_var);
        txt_marital_status_var = view.findViewById(R.id.txt_marital_status_var);
        txt_military_status_var = view.findViewById(R.id.txt_military_status_var);


    }

    private void coordinateMotion() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float seekPosition = -verticalOffset / (float) appBarLayout.getTotalScrollRange();
                motionLayout.setProgress(seekPosition);

            }
        });
    }


    private void onViewsClicked() {
        img_instagram.setOnClickListener(v -> helper.openSelectedApp(INSTAGRAM_PACKAGE_NAME,helper.INSTAGRAM_URL));
        img_linkedin.setOnClickListener(v -> helper.openSelectedApp(LINKEDIN_PACKAGE_NAME, LINKEDIN_URL));
        img_github.setOnClickListener(v -> helper.openSelectedApp(null, GITHUB_URL));
        img_whatsapp.setOnClickListener(v -> helper.openWhatsApp(v));
        img_fb.setOnClickListener(v -> helper.openSelectedApp(FB_PACKAGE_NAME,FB_URL_Browser));
        txt_call.setOnClickListener(v -> makePhoneCall());

    }


    private void fillViewsWithProfileData(){
        viewModel.getProfileData().observe(getViewLifecycleOwner(), new Observer<ProfileModel>() {
            @Override
            public void onChanged(ProfileModel profileModel) {
                txt_name.setText(profileModel.getName());
                txt_email.setText(profileModel.getEmail());
                txt_gender_var.setText(profileModel.getGender());
                txt_marital_status_var.setText(profileModel.getMaritalStatus());
                txt_military_status_var.setText(profileModel.getMilitaryStatus());
                txt_nationality_var.setText(profileModel.getNationality());
            }
        });
    }


    private void makeCall(){
       startActivity(viewModel.makeCall(getString(R.string.my_num)));
    }



    private void makePhoneCall() {
            if (isPhonePermissionGranted()) {
                String dial = "tel:" + getString(R.string.my_num);
                startActivity(viewModel.makeCall(getString(R.string.my_num)));
            }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(getContext(), "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
