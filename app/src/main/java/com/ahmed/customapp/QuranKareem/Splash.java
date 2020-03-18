package com.ahmed.customapp.QuranKareem;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.ahmed.customapp.BaseActivity;
import com.ahmed.customapp.R;

import androidx.constraintlayout.motion.widget.MotionLayout;

public class Splash extends BaseActivity {


    private MotionLayout quran_motion_layout;
    private ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        quran_motion_layout = findViewById(R.id.quran_motion_layout);
        background = findViewById(R.id.full_background_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                quran_motion_layout.setTransitionDuration(1000);
                quran_motion_layout.transitionToEnd();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(activity,FragmentsContainer.class);

                        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        ActivityOptions options = ActivityOptions
                                .makeSceneTransitionAnimation(Splash.this,background, "robot");
                        startActivity(intent, options.toBundle());
                        finish();
                    }
                },1005);
            }
        },2000);


    }
}