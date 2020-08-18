package com.ahmed.customapp.MainApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.ahmed.customapp.BroadcastReceivers.TestServices;
import com.ahmed.customapp.Calulator.CalculatorActivity;
import com.ahmed.customapp.CelebrationActivity;


import com.ahmed.customapp.Services.ForegroundServices.ForegroundServiceActivity;
import com.ahmed.customapp.Services.JobIntentService.JobIntentServiceActivity;
import com.ahmed.customapp.Services.JobScheduler.JobSchedulerActivity;
import com.ahmed.customapp.MyAppsActivity;
import com.ahmed.customapp.QRCodeActivity;
import com.ahmed.customapp.QuranKareem.OuranSplash;
import com.ahmed.customapp.R;
import com.ahmed.customapp.Test.AnimateActivity;
import com.ahmed.customapp.Torch.TorchActivity;
import com.ahmed.customapp.WeActivity;

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
    private Button btn_QRCodeActivity;
    private Button btn_jobSchedulerActivity;
    private Button btn_ForegroundActivity;
    private Button btn_JobIntentServiceActivity;
    private Button btn_TorchActivity;


    private static final int QURAN = 0;
    private static final int CALCULATOR = 1;
    private static final int MY_APPS = 2;
    private static final int WE = 3;
    private static final int MAIN_ACTIVITY = 4;
    private static final int ANIMATE_ACTIVITY = 5;
    private static final int CELEBRATE_ACTIVITY = 6;
    private static final int QR_ACTIVITY = 7;
    private static final int JOB_SCHEDULER_ACTIVITY = 8;
    private static final int FOREGROUND_ACTIVITY = 9;
    private static final int JOB_INTENT_SERVICE_ACTIVITY = 10;
    private static final int TORCH_ACTIVITY = 11;


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
        Intent serviceIntent = new Intent(this, TestServices.class);
        ContextCompat.startForegroundService(this, serviceIntent);



        boolean b = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
      /*  Camera cam = Camera.open();
        Camera.Parameters p = cam.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam.setParameters(p);
        cam.startPreview();*/


        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String cameraId = null;
            try {
                cameraId = camManager.getCameraIdList()[0];
                camManager.setTorchMode(cameraId, true);   //Turn ON
                Log.e(TAG, "onCreate: isFlashOpened" +  b);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }*/ /*else {
            mCamera = Camera.open();
            parameters = mCamera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(parameters);
            mCamera.startPreview();
        }*/
        //TestServices testServices = new TestServices();
        /*IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        MyExampleBroadcastReceiver myExampleBroadcastReceiver =  new MyExampleBroadcastReceiver();
        registerReceiver(myExampleBroadcastReceiver, filter);*/
    }


    private void initViews(){
        btn_quran = findViewById(R.id.btn_quran);
        btn_calculator = findViewById(R.id.btn_calculator);
        btn_myApps = findViewById(R.id.btn_myApps);
        btn_We = findViewById(R.id.btn_We);
        btn_MainActivity = findViewById(R.id.btn_MainActivity);
        btn_animate = findViewById(R.id.btn_animate);
        btn_celebrate = findViewById(R.id.btn_celebrate);
        btn_QRCodeActivity = findViewById(R.id.btn_QRCodeActivity);
        btn_jobSchedulerActivity = findViewById(R.id.btn_jobSchedulerActivity);
        btn_ForegroundActivity = findViewById(R.id.btn_ForegroundActivity);
        btn_JobIntentServiceActivity = findViewById(R.id.btn_jobIntentServiceActivity);
        btn_TorchActivity = findViewById(R.id.btn_TorchActivity);

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
        btn_celebrate.setOnClickListener(v -> { openActivity(CELEBRATE_ACTIVITY); });
        btn_QRCodeActivity.setOnClickListener(v -> openActivity(QR_ACTIVITY));
        btn_jobSchedulerActivity.setOnClickListener(v -> openActivity(JOB_SCHEDULER_ACTIVITY));
        btn_ForegroundActivity.setOnClickListener(v -> openActivity(FOREGROUND_ACTIVITY));
        btn_JobIntentServiceActivity.setOnClickListener(v -> openActivity(JOB_INTENT_SERVICE_ACTIVITY));
        btn_TorchActivity.setOnClickListener(v -> openActivity(TORCH_ACTIVITY));

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


    private void openActivity(int activityCode){
        Intent intent;
        switch (activityCode){
            case QURAN :
                intent = new Intent(this, OuranSplash.class);
                break;
            case CALCULATOR :
                intent = new Intent(this, CalculatorActivity.class);
                break;
            case MY_APPS :
                intent = new Intent(this, MyAppsActivity.class);
                break;
            case WE :
                intent = new Intent(this, WeActivity.class);
                break;
            case ANIMATE_ACTIVITY :
                intent = new Intent(this, AnimateActivity.class);
                break;
            case CELEBRATE_ACTIVITY :
                intent = new Intent(this, CelebrationActivity.class);
                break;
            case QR_ACTIVITY :
                intent = new Intent(this, QRCodeActivity.class);
                break;
            case JOB_SCHEDULER_ACTIVITY:
                intent = new Intent(this, JobSchedulerActivity.class);
                break;
            case FOREGROUND_ACTIVITY:
                intent = new Intent(this, ForegroundServiceActivity.class);
                break;
            case JOB_INTENT_SERVICE_ACTIVITY:
                intent = new Intent(this, JobIntentServiceActivity.class);
                break;
            case TORCH_ACTIVITY:
                intent = new Intent(this, TorchActivity.class);
                break;
            case MAIN_ACTIVITY :
            default:
                intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
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





}
