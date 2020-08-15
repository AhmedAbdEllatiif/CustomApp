package com.ahmed.customapp.Services.ForegroundServices;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.ahmed.customapp.App;
import com.ahmed.customapp.R;

import java.util.Random;

public class ExampleService extends Service {


    public static final String NOTIFICATION_DESC = "NOTIFICATION_DESC";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * These tell us what happened when the system kills our servers
     * START_NOT_STICKY ==>
     * START_STICKY
     * START_REDELIVER_INTENT
     */
    Notification notification;
    int seconds = 0;
    String input = "Seconds ==> " + seconds;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

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


       startServiceWithCounter();


        return START_NOT_STICKY;
    }

    private void startServiceWithCounter(){
        Intent notificationIntent = new Intent(this, ForegroundServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification.Builder builder = new Notification.Builder(ExampleService.this, App.CHANNEL_ID);
                    builder.setContentTitle("Example Service");
                    builder.setSmallIcon(R.drawable.ic_book);
                    builder.setContentIntent(pendingIntent);
                    builder.setContentText(input);
                    notification = builder.build();
                 startForeground(1, notification);
        NotificationManager notificationManager =
                (NotificationManager) ExampleService.this.getSystemService(Context.NOTIFICATION_SERVICE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    sleep(1);
                    seconds = i;
                    input = "Seconds ==> " + seconds;
                    builder.setContentText(input);
                    notification = builder.build();
                    notificationManager.notify(1, notification);
                }
                input = "Finished Successfully";
                notification = builder.build();
                builder.setContentText(input);
                    notificationManager.notify(1, notification);
                sleep(3);
                stopSelf();
            }
        }).start();
    }

    /**
     * To sleep for seconds
     * */
    private void sleep(int seconds){
        try {
            int time = seconds * 1000;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
