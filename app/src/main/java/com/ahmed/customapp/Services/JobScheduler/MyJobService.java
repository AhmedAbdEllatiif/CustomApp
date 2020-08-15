package com.ahmed.customapp.Services.JobScheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class MyJobService extends JobService {

    private static final String TAG = "MyJobService";
    private boolean jobCancelled = false;

    public static final int JOB_ID = 123;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.e(TAG, "onStartJob");
        donBackgroundWork(jobParameters);
        return true;
    }

    private void donBackgroundWork(JobParameters parameters){
        Log.e(TAG, "donBackgroundWork: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i < 10; i++){
                    Log.e(TAG, "donBackgroundWork: i = " + i );
                    Log.e(TAG, "donBackgroundWork: jobCancelled ==> " + jobCancelled);
                    if (jobCancelled) {
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    jobFinished(parameters,false);
                }
                    Log.e(TAG, "donBackgroundWork: Job is done successfully");
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.e(TAG, "job cancelled before completion");
        jobCancelled = true;
        return true;
    }


}
