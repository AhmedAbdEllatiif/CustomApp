package com.ahmed.customapp.MainApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.ahmed.customapp.Calulator.CalculatorActivity;
import com.ahmed.customapp.CelebrationActivity;

import com.ahmed.customapp.MainApp.ShowCaseLibrary.ShowcaseManager;
import com.ahmed.customapp.MyAppsActivity;
import com.ahmed.customapp.QuranKareem.OuranSplash;
import com.ahmed.customapp.R;
import com.ahmed.customapp.Test.AnimateActivity;
import com.ahmed.customapp.WeActivity;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MasterActivity extends AppCompatActivity {

    private static final String TAG = "MasterActivity";

    private Button btn_quran;
    private Button btn_calculator;
    private Button btn_myApps;
    private Button btn_We;
    private Button btn_MainActivity;
    private Button btn_animate;
    private Button btn_celebrate;


    private static final int QURAN = 0;
    private static final int CALCULATOR = 1;
    private static final int MY_APPS = 2;
    private static final int WE = 3;
    private static final int MAIN_ACTIVITY = 4;
    private static final int ANIMATE_ACTIVITY = 5;
    private static final int CELEBRATE_ACTIVITY = 6;


    //Fab
    FloatingActionButton main_fab;
    FloatingActionButton  fab_map;
    FloatingActionButton  fab_cam;
    FloatingActionButton fab_mic;
    TextView txt_mic;
    TextView txt_cam;
    TextView txt_map;

    boolean isFABOpen = false;

    FloatingActionButton  mic_test;
    FloatingActionButton  cam_test;
    FloatingActionButton map_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);


        initViews();

        onViewClicked();

       // startWithMainFab();


        //fab_show_case();
        //closeFABMenu();

        //sequence();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fab_show_case();
            }
        },1000);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void initViews(){
        btn_quran = findViewById(R.id.btn_quran);
        btn_calculator = findViewById(R.id.btn_calculator);
        btn_myApps = findViewById(R.id.btn_myApps);
        btn_We = findViewById(R.id.btn_We);
        btn_MainActivity = findViewById(R.id.btn_MainActivity);
        btn_animate = findViewById(R.id.btn_animate);
        btn_celebrate = findViewById(R.id.btn_celebrate);

        //fab
        main_fab = (FloatingActionButton) findViewById(R.id.fab);
        fab_map = (FloatingActionButton) findViewById(R.id.fab_map);
        fab_cam = (FloatingActionButton) findViewById(R.id.fab_cam);
        fab_mic = (FloatingActionButton) findViewById(R.id.fab_mic);
        txt_mic =  findViewById(R.id.txt_mic);
        txt_cam =  findViewById(R.id.txt_cam);
        txt_map =  findViewById(R.id.txt_map);

        mic_test = (FloatingActionButton) findViewById(R.id.mic_test);
        cam_test = (FloatingActionButton) findViewById(R.id.cam_test);
        map_test = (FloatingActionButton) findViewById(R.id.map_test);





        Animation txtAnim = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        txtAnim.setDuration(100);

        txt_mic.startAnimation(txtAnim);
        txt_cam.startAnimation(txtAnim);
        txt_map.startAnimation(txtAnim);


    }


    private void onViewClicked(){
        btn_quran.setOnClickListener(v -> openActivity(QURAN));
        btn_calculator.setOnClickListener(v -> openActivity(CALCULATOR));
        btn_myApps.setOnClickListener(v -> openActivity(MY_APPS));
        btn_We.setOnClickListener(v -> openActivity(WE));
        btn_MainActivity.setOnClickListener(v -> openActivity(MAIN_ACTIVITY));
        btn_animate.setOnClickListener(v -> openActivity(ANIMATE_ACTIVITY));
        btn_celebrate.setOnClickListener(v -> {
            fab_show_case();
           // openActivity(CELEBRATE_ACTIVITY);
        });

        onFabClicked();
    }


    private void openActivity(int activityCode){
        Intent intent;
        switch (activityCode){
            case QURAN :
                intent = new Intent(this, OuranSplash.class);
                sequence();
                break;
            case CALCULATOR :
                intent = new Intent(this, CalculatorActivity.class);
                break;
            case MY_APPS :
                intent = new Intent(this, MyAppsActivity.class);
                simple();
                break;
            case WE :
                intent = new Intent(this, WeActivity.class);
                break;
            case ANIMATE_ACTIVITY :
                intent = new Intent(this, AnimateActivity.class);
                newShowCase();
                break;
            case CELEBRATE_ACTIVITY :
                intent = new Intent(this, CelebrationActivity.class);
                newShowCase_roundedRectangle();
                break;
            case MAIN_ACTIVITY :
            default:
                intent = new Intent(this, MainActivity.class);
        }

        //startActivity(intent);
    }


    private void onFabClicked(){
        main_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });
    }

    private void showFABMenu(){
        isFABOpen=true;
        AnimationSet mic_text_set = new AnimationSet(true);
        AnimationSet cam_text_set = new AnimationSet(true);
        AnimationSet map_text_set = new AnimationSet(true);
        Animation rotate_right = AnimationUtils.loadAnimation(this,R.anim.rotate_right);
        Animation fade_out = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        TranslateAnimation translate_mic = new TranslateAnimation(
                0, 0, 0, -getResources().getDimension(R.dimen.standard_155));
        TranslateAnimation translate_cam = new TranslateAnimation(
                0, 0, 0, -getResources().getDimension(R.dimen.standard_105));
        TranslateAnimation translate_map = new TranslateAnimation(
                0, 0, 0, -getResources().getDimension(R.dimen.standard_55));


        //duration
        translate_mic.setDuration(500);
        translate_cam.setDuration(500);
        translate_map.setDuration(500);

        //set fillAfter
        translate_mic.setFillAfter(true);
        translate_cam.setFillAfter(true);
        translate_map.setFillAfter(true);
        mic_text_set.setFillAfter(true);
        cam_text_set.setFillAfter(true);
        map_text_set.setFillAfter(true);



        //Add texts animation
        mic_text_set.addAnimation(translate_mic);
        mic_text_set.addAnimation(fade_out);

        cam_text_set.addAnimation(translate_cam);
        cam_text_set.addAnimation(fade_out);

        map_text_set.addAnimation(translate_map);
        map_text_set.addAnimation(fade_out);


         //fab ==> start animation
        main_fab.startAnimation(rotate_right);
        fab_mic.startAnimation(translate_mic);
        fab_cam.startAnimation(translate_cam);
        fab_map.startAnimation(translate_map);

        //texts ==> start animation
        txt_mic.startAnimation(mic_text_set);
        txt_cam.startAnimation(cam_text_set);
        txt_map.startAnimation(map_text_set);

        translate_mic.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void closeFABMenu(){
        isFABOpen=false;

        TranslateAnimation translate_mic = new TranslateAnimation(
                0, 0, -getResources().getDimension(R.dimen.standard_155),0);

        TranslateAnimation translate_cam = new TranslateAnimation(
                0, 0, -getResources().getDimension(R.dimen.standard_105), 0);

        TranslateAnimation translate_map = new TranslateAnimation(
                0, 0, -getResources().getDimension(R.dimen.standard_55), 0);


        AnimationSet mic_text_set = new AnimationSet(true);
        AnimationSet cam_text_set = new AnimationSet(true);
        AnimationSet map_text_set = new AnimationSet(true);
        Animation rotate_left = AnimationUtils.loadAnimation(this,R.anim.roatate_left);
        Animation fade_in = AnimationUtils.loadAnimation(this,R.anim.fade_in);

        //add duration
        translate_mic.setDuration(500);
        translate_map.setDuration(500);
        translate_cam.setDuration(500);

        //set fillAfter
        translate_mic.setFillAfter(true);
        translate_map.setFillAfter(true);
        translate_cam.setFillAfter(true);
        mic_text_set.setFillAfter(true);
        cam_text_set.setFillAfter(true);
        map_text_set.setFillAfter(true);

        //Add texts animation
        mic_text_set.addAnimation(translate_mic);
        mic_text_set.addAnimation(fade_in);

        cam_text_set.addAnimation(translate_cam);
        cam_text_set.addAnimation(fade_in);

        map_text_set.addAnimation(translate_map);
        map_text_set.addAnimation(fade_in);

        //fab ==> start animation
        main_fab.startAnimation(rotate_left);
        fab_map.startAnimation(translate_map);
        fab_cam.startAnimation(translate_cam);
        fab_mic.startAnimation(translate_mic);

        //texts ==> start animation
        txt_mic.startAnimation(mic_text_set);
        txt_cam.startAnimation(cam_text_set);
        txt_map.startAnimation(map_text_set);


    }

    /*private void sequence(){
       TapTarget quranTarget = TapTarget.forView(findViewById(R.id.btn_quran), "This is Quran") .transparentTarget(true);
       TapTarget calculatorTarget = TapTarget.forView(findViewById(R.id.btn_calculator), "This is Calculator")
               .targetRadius(60)
               .transparentTarget(true);

        new TapTargetSequence(this)
                .targets(
                        quranTarget,
                        calculatorTarget,
                        TapTarget.forView(findViewById(R.id.btn_myApps), "You", "Up") .transparentTarget(true),
                        TapTarget.forView(findViewById(R.id.btn_We), "You", "Up") .transparentTarget(true)
                                .dimColor(android.R.color.holo_red_dark)
                                .outerCircleColor(R.color.lightGray)
                                .targetCircleColor(R.color.colorPrimary)
                                .textColor(android.R.color.black),
                        TapTarget.forBounds(new Rect(), "Down", ":^)")
                                .cancelable(true)
                                .icon(getDrawable(R.drawable.calculator)))
                .listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                        // Yay
                        Log.e(TAG, "onSequenceFinish: ");
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                        Log.e(TAG, "onSequenceStep: ");
                    }


                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        // Boo
                        Log.e(TAG, "onSequenceCanceled: ");
                    }
                }).start();
    }*/

    private void sequence(){

        TapTarget quranTarget = TapTarget.forView(findViewById(R.id.btn_quran), "This is Quran") .transparentTarget(true);
        TapTarget calculatorTarget = TapTarget.forView(findViewById(R.id.btn_calculator), "This is Calculator")
                .targetRadius(60)
                .transparentTarget(true);
        TapTarget weTarget = TapTarget.forView(findViewById(R.id.btn_We), "This is We")
                .targetRadius(60)
                .transparentTarget(true);
        Rect droidTarget = new Rect(0, 0, btn_myApps.getWidth() * 2, btn_myApps.getHeight() * 2);
        TapTarget mainBtnTarget = TapTarget.forBounds(droidTarget,"Hi Ahmed is Awesome");

        new TapTargetSequence(this)
                .targets(
                        quranTarget,
                        calculatorTarget,
                        TapTarget.forView(findViewById(R.id.btn_myApps), "You", "Up") .transparentTarget(true),
                        weTarget,
                        mainBtnTarget)
                .listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                        // Yay
                        Log.e(TAG, "onSequenceFinish: ");
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                        Log.e(TAG, "onSequenceStep: ");
                    }


                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        // Boo
                        Log.e(TAG, "onSequenceCanceled: ");
                    }
                }).start();
    }

    private void simple(){

        // Using deprecated methods makes you look way cool
        //droidTarget.offset(display.getWidth() / 2, display.getHeight() / 2);
        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.btn_myApps), "This is a target",
                        "We have the best targets, believe me")
                        // All options below are optional
                        .outerCircleColor(R.color.colorPrimaryDark)      // Specify a color for the outer circle
                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
                        .titleTextSize(20)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.white)      // Specify the color of the title text
                        .descriptionTextSize(10)            // Specify the size (in sp) of the description text
                        .descriptionTextColor(R.color.red)  // Specify the color of the description text
                        .textColor(R.color.white)           // Specify a color for both the title and description text
                        .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                        .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)                   // Whether to tint the target view's color
                        .transparentTarget(true)           // Specify whether the target is transparent (displays the content underneath)
                        //.icon(getDrawable(R.drawable.calculator))                     // Specify a custom drawable to draw as the target
                        .targetRadius(60),                  // Specify the target radius (in dp)
                new TapTargetView.Listener() {              // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional
                        Log.e(TAG, "onTargetClick: ");
                        Intent intent = new Intent(MasterActivity.this, MyAppsActivity.class);
                        startActivity(intent);
                    }
                });


    }


    private void fab_show_case(){
        Log.e(TAG, "fab_show_case: " );
        Log.e(TAG, "fab_show_case =>> btn_celebrate.getTop ==> " +  btn_celebrate.getTop());
        Log.e(TAG, "fab_show_case =>> btn_celebrate.getBottom ==> " +  btn_celebrate.getBottom());

        //btn_MainActivity.setVisibility(View.INVISIBLE);
        ShowcaseManager.Builder builder = new ShowcaseManager.Builder();
        builder.context(MasterActivity.this)
                .roundedRectangle()
                .key("KEY")
                .developerMode(true)
                .view(btn_celebrate)
                //.descriptionImageRes(R.mipmap.ic_launcher)
                .descriptionTitle("LOREM IPSUM")
                .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .buttonText("Done")
                .marginFocusArea(2)
                .add()


                //.key("KEY")
                //.developerMode(true)
                .view(btn_animate)
                .roundedRectangle()
                //.descriptionImageRes(R.mipmap.ic_launcher)
                .descriptionTitle("LOREM IPSUM")
                .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .buttonText("Done")
               // .marginFocusArea(50)
                .add()

                .view(btn_MainActivity)
                .roundedRectangle()
                //.descriptionImageRes(R.mipmap.ic_launcher)
                .key("KEY")
                .developerMode(true)
                .descriptionTitle("LOREM IPSUM")
                .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .buttonText("Done")
                .add()
                .build()
                .show();
    }

    private void startWithMainFab(){
        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.fab), "This is a target",
                        "Press on the Add button to select from menu")
                        // All options below are optional
                        .outerCircleColor(R.color.colorPrimaryDark)      // Specify a color for the outer circle
                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
                        .titleTextSize(20)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.white)      // Specify the color of the title text
                        .descriptionTextSize(10)            // Specify the size (in sp) of the description text
                        .descriptionTextColor(R.color.red)  // Specify the color of the description text
                        .textColor(R.color.white)           // Specify a color for both the title and description text
                        .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                        .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)                   // Whether to tint the target view's color
                        .transparentTarget(true)           // Specify whether the target is transparent (displays the content underneath)
                        //.icon(getDrawable(R.drawable.calculator))                     // Specify a custom drawable to draw as the target
                        .targetRadius(40),                  // Specify the target radius (in dp)
                new TapTargetView.Listener() {              // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional
                        Log.e(TAG, "onTargetClick: ");
                        showFABMenu();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                fab_show();
                            }
                        },500);

                    }
                });
    }


    private void fab_show(){
       /* TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.fab_mic), "This is a target",
                        "We have the best targets, believe me")
                        // All options below are optional
                        .outerCircleColor(R.color.colorPrimaryDark)      // Specify a color for the outer circle
                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                        .targetRadius(30)
                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
                        .titleTextSize(20)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.white)      // Specify the color of the title text
                        .descriptionTextSize(10)            // Specify the size (in sp) of the description text
                        .descriptionTextColor(R.color.red)  // Specify the color of the description text
                        .textColor(R.color.white)           // Specify a color for both the title and description text
                        .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                        .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)                   // Whether to tint the target view's color
                        .transparentTarget(true)  ,         // Specify whether the target is transparent (displays the content underneath)
                        //.icon(getDrawable(R.drawable.calculator))                     // Specify a custom drawable to draw as the target
                                        // Specify the target radius (in dp)
                new TapTargetView.Listener() {              // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional
                        Log.e(TAG, "onTargetClick: ");
                        Intent intent = new Intent(MasterActivity.this, MyAppsActivity.class);
                        startActivity(intent);
                    }
                });*/
        TapTarget main_target = TapTarget.forView(findViewById(R.id.fab), "This is Mic")
                .targetRadius(40)
                .transparentTarget(true);

        TapTarget mic_target = TapTarget.forView(findViewById(R.id.mic_test), "This is Mic")
                .targetRadius(25)
                .transparentTarget(true);

        TapTarget cam_target = TapTarget.forView(findViewById(R.id.cam_test), "This is Cam")
                .targetRadius(30)
                .transparentTarget(true);

        TapTarget map_target = TapTarget.forView(findViewById(R.id.map_test), "This is Map")
                .targetRadius(35)
                .transparentTarget(true);

        new TapTargetSequence(this)
                .targets(
                        map_target,
                        cam_target,
                        mic_target)
                .listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                        // Yay
                        Log.e(TAG, "onSequenceFinish: ");
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                        Log.e(TAG, "onSequenceStep: ");
                    }


                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        // Boo
                        Log.e(TAG, "onSequenceCanceled: ");
                    }
                }).start();
    }



    private void newShowCase(){
        /*ShowcaseManager.Builder builder = new ShowcaseManager.Builder();
        builder.context(MasterActivity.this)
                .rectangle()
                .key("KEY")
                .developerMode(true)
                .view(btn_animate)
                //.descriptionImageRes(R.mipmap.ic_launcher)
                .descriptionTitle("LOREM IPSUM")
                .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .buttonText("Done")
                .add()
                .build()
                .show();*/
    }

    private void newShowCase_roundedRectangle(){
       /* ShowcaseManager.Builder builder = new ShowcaseManager.Builder();

        builder.context(MasterActivity.this)
                .roundedRectangle()
                . marginFocusArea(7)
                .key("KEY")
                .developerMode(true)
                .view(btn_celebrate)
                //.descriptionImageRes(R.mipmap.ic_launcher)
                .descriptionTitle("LOREM IPSUM")
                .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .buttonText("Done")
                .add()

                .view(btn_quran)
                .roundedRectangle()
                .descriptionImageRes(R.mipmap.ic_launcher_round)
                .descriptionTitle("LOREM IPSUM DOLOR-2")
                .descriptionText("Consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                .buttonText("Cancel")
                .marginFocusArea(7)
                .add()
                .build()
                .show();*/
    }
}
