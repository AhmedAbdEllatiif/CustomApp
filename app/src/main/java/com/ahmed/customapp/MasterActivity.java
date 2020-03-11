package com.ahmed.customapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ahmed.customapp.Calulator.CalculatorActivity;
import com.ahmed.customapp.QuranKareem.Splash;

public class MasterActivity extends AppCompatActivity {


    private Button btn_quran;
    private Button btn_calculator;


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
    }


    private void onViewClicked(){
        btn_quran.setOnClickListener(v -> openQuran());
        btn_calculator.setOnClickListener(v -> openCalculator());
    }

    private void openQuran(){
        Intent intent = new Intent(this, Splash.class);
        startActivity(intent);
    }

    private void openCalculator(){
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }
}
