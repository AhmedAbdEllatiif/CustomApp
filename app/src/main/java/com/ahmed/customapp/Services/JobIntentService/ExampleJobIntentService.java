package com.ahmed.customapp.Services.JobIntentService;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

/**
 * Helps you to make a foreground service executed a background thread automatically
 * Enqueue every new Intent sent one after another
 * try send a then ab then abc from the Activity
 */
public class ExampleJobIntentService extends JobIntentService {

    private static final String TAG = "ExampleJobIntentService";

    public static final String NOTIFICATION_DESC = "NOTIFICATION_DESC";

    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, ExampleJobIntentService.class, 123, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.e(TAG, "onHandleWork");
        String input = intent.getStringExtra(NOTIFICATION_DESC);
        for (int i = 0; i < 10; i++) {
            Log.e(TAG, input + " - " + i);
            if (isStopped()) return;
            SystemClock.sleep(1000);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
    @Override
    public boolean onStopCurrentWork() {
        Log.e(TAG, "onStopCurrentWork");
        return super.onStopCurrentWork();
    }

}
