package com.ahmed.customapp.Test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.ahmed.customapp.R;

public class AnimateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);

        MotionLayout motionLayout;
        motionLayout = findViewById(R.id.MyMotion);
        ImageView imageView;
        imageView = findViewById(R.id.my_img_fb);
       // motionLayout.startViewTransition(imageView);
        //motionLayout.transitionToStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                motionLayout.setTransitionDuration(1000);
        motionLayout.transitionToEnd();

            }
        },2000);
    }
}
