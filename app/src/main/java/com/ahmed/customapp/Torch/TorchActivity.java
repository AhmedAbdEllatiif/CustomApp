package com.ahmed.customapp.Torch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.ahmed.customapp.R;

public class TorchActivity extends AppCompatActivity {

    Button btn_startLighting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torch_main);

        btn_startLighting = findViewById(R.id.btn_startLighting);

        btn_startLighting.setOnClickListener(view -> beginLighting());




    }

    private void beginLighting(){
        TorchProvider.Builder builder = new TorchProvider().Builder(this);
        builder.repeatTimes(10)
                .intervalTime(100)
                .waitFor(5000)
                .build();
    }
}