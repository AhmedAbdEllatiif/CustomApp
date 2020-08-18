package com.ahmed.customapp.BroadcastReceivers;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.ahmed.customapp.App;
import com.ahmed.customapp.MainApp.MasterActivity;
import com.ahmed.customapp.R;


public class TestServices extends Service {

    MyExampleBroadcastReceiver myExampleBroadcastReceiver = new MyExampleBroadcastReceiver();
   public static final String ACTION_SCREEN_ON = Intent.ACTION_SCREEN_ON;
   public static final String ACTION_SCREEN_OFF = Intent.ACTION_SCREEN_OFF;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerReceiver(myExampleBroadcastReceiver,new IntentFilter(Intent.ACTION_SCREEN_ON));
        //registerReceiver(myExampleBroadcastReceiver,new IntentFilter(Intent.ACTION_SCREEN_OFF));
        Intent notificationIntent = new Intent(this, MasterActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText("Test")
                .setSmallIcon(R.drawable.ic_book)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);



        /*for (int i = 0; i < 10; i++) {
            turnOnTorch();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            turnOFFTorch();
        }*/


        //stopSelf();
         /*//Default service
        String input = intent.getStringExtra(NOTIFICATION_DESC);
        Intent notificationIntent = new Intent(this, ForegroundServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_book)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        //do heavy work on a background thread
        //stopSelf(); //must stopSelf after finishing its work*/
        return START_STICKY;
    }

    private void turnOnTorch(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String cameraId = null;
            try {
                cameraId = camManager.getCameraIdList()[0];
                camManager.setTorchMode(cameraId, true);   //Turn ON
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

    }
    private void turnOFFTorch(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                String cameraId;
                CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                if (camManager != null) {
                    cameraId = camManager.getCameraIdList()[0]; // Usually front camera is at 0 position.
                    camManager.setTorchMode(cameraId, false);
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //startForegroundService(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(myExampleBroadcastReceiver);

        super.onDestroy();
    }
}
