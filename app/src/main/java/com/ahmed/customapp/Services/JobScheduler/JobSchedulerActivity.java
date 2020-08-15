package com.ahmed.customapp.Services.JobScheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.ahmed.customapp.BaseClasses.BaseActivity;
import com.ahmed.customapp.R;

public class JobSchedulerActivity extends BaseActivity {

    private static final String TAG = "JobSchedulerActivity";

    private Button btn_startJob;
    private Button btn_cancelJob;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler);

        initViews();

        onViewsClicked();


    }

    private void initViews(){
        btn_startJob = findViewById(R.id.btn_startJob);
        btn_cancelJob = findViewById(R.id.btn_cancelJob);
    }


    private void onViewsClicked(){
        btn_startJob.setOnClickListener(view -> {
            ComponentName componentName = new ComponentName(this, MyJobService.class);
            JobInfo info = new JobInfo.Builder(MyJobService.JOB_ID, componentName)
                    .setRequiresCharging(false)
                    //.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED) //with wifi
                    .setPersisted(true) //keep the job alive even we reboot the device
                    //.setPeriodic(15 * 60 * 100)
                    .build();

            JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            int result = jobScheduler.schedule(info);
            if (result == JobScheduler.RESULT_SUCCESS) {
                Log.e(TAG, "onViewsClicked: JobScheduler is RESULT_SUCCESS");
            } else {
                Log.e(TAG, "onViewsClicked: JobScheduler is RESULT_FAILURE");
            }
        });

        btn_cancelJob.setOnClickListener(view -> {
            JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            jobScheduler.cancel(MyJobService.JOB_ID);
            jobScheduler.cancel(MyJobService.JOB_ID);
            //jobScheduler.cancelAll();
            //Log.e(TAG, "cancel clicked: ");
        });
    }
}
