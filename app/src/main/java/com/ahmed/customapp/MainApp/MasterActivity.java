package com.ahmed.customapp.MainApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ahmed.customapp.Calulator.CalculatorActivity;
import com.ahmed.customapp.MainApp.MainActivity;
import com.ahmed.customapp.MainApp.ProfileFragment;
import com.ahmed.customapp.MyAppsActivity;
import com.ahmed.customapp.QuranKareem.Splash;
import com.ahmed.customapp.R;
import com.ahmed.customapp.Test.AnimateActivity;
import com.ahmed.customapp.WeActivity;

public class MasterActivity extends AppCompatActivity {


    private Button btn_quran;
    private Button btn_calculator;
    private Button btn_myApps;
    private Button btn_Profile;
    private Button btn_We;
    private Button btn_MainActivity;
    private Button btn_animate;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);


        initViews();

        onViewClicked();

    }

    private void initViews(){
        btn_quran = findViewById(R.id.btn_quran);
        btn_calculator = findViewById(R.id.btn_calculator);
        btn_myApps = findViewById(R.id.btn_myApps);
        btn_Profile = findViewById(R.id.btn_Profile);
        btn_We = findViewById(R.id.btn_We);
        btn_MainActivity = findViewById(R.id.btn_MainActivity);
        btn_animate = findViewById(R.id.btn_animate);
    }


    private void onViewClicked(){
        btn_quran.setOnClickListener(v -> openQuran());
        btn_calculator.setOnClickListener(v -> openCalculator());
        btn_myApps.setOnClickListener(v -> openMyApps());
        btn_Profile.setOnClickListener(v -> openMyProfile());
        btn_We.setOnClickListener(v -> btn_We());
        btn_MainActivity.setOnClickListener(v -> openMainActivity());
        btn_animate.setOnClickListener(v -> openAnimateActivity());
    }

    private void openQuran(){
        Intent intent = new Intent(this, Splash.class);
        startActivity(intent);
    }

    private void openCalculator(){
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    private void openMyApps(){
        Intent intent = new Intent(this, MyAppsActivity.class);
        startActivity(intent);
    }

    private void openMyProfile(){
        Intent intent = new Intent(this, ProfileFragment.class);
        startActivity(intent);
    }

    private void btn_We(){
        Intent intent = new Intent(this, WeActivity.class);
        startActivity(intent);
    }

    private void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openAnimateActivity(){
        Intent intent = new Intent(this, AnimateActivity.class);
        startActivity(intent);
    }
}