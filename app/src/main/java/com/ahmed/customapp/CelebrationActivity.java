package com.ahmed.customapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import static nl.dionsegijn.konfetti.models.Shape.RECT;

public class CelebrationActivity extends AppCompatActivity {

    private KonfettiView viewKonfetti;
    private Button btn_streamFor;
    private Button btn_burst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebration);


        initViews();

        onViewsClicked();

    }

    private void initViews() {
        viewKonfetti = findViewById(R.id.viewKonfetti);
        btn_streamFor = findViewById(R.id.btn_streamFor);
        btn_burst = findViewById(R.id.btn_burst);
    }


    private void onViewsClicked() {
        btn_streamFor.setOnClickListener(v -> streamCelebrate());


        btn_burst.setOnClickListener(v -> burstCelebrate());
    }

    private void streamCelebrate() {
        viewKonfetti.build()
                // .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .addColors(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.red)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.CIRCLE, RECT)
                .addSizes(new Size(12, 5))
                .setPosition(-50f, viewKonfetti.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 5000L);
    }


    private void burstCelebrate() {


        viewKonfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.CIRCLE, Shape.RECT)
                .addSizes(new Size(12, 5))
                .setPosition(viewKonfetti.getX() + viewKonfetti.getWidth() / 2, viewKonfetti.getY() + viewKonfetti.getHeight() / 3)
                .burst(300);

    }


}
