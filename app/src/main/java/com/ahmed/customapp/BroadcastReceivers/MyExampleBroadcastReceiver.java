package com.ahmed.customapp.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import com.ahmed.customapp.Torch.TorchProvider;

public class MyExampleBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyExampleBroadcastRecei";



    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive: " );
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            Toast.makeText(context, "ACTION_SCREEN_ON", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onReceive: ACTION_SCREEN_ON");
        }
        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            Toast.makeText(context, "ACTION_SCREEN_OFF", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onReceive: ACTION_SCREEN_OFF");
        }


        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            Toast.makeText(context, "Connectivity Changed", Toast.LENGTH_SHORT).show();
        }

    }
}
