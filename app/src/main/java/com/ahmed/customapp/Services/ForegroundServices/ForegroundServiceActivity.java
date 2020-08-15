package com.ahmed.customapp.Services.ForegroundServices;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.ahmed.customapp.BaseClasses.BaseActivity;
import com.ahmed.customapp.R;

public class ForegroundServiceActivity extends BaseActivity{

    private EditText editTextInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground_service);
        editTextInput = findViewById(R.id.edit_text_input);

    }
    public void startService(View v) {
        String input = editTextInput.getText().toString();
        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra(ExampleService.NOTIFICATION_DESC, input);
        //startService(serviceIntent);
        ContextCompat.startForegroundService(this, serviceIntent); //best practice if the app is killed
    }
    public void stopService(View v) {
        Intent serviceIntent = new Intent(this, ExampleService.class);
        stopService(serviceIntent);
    }
}
