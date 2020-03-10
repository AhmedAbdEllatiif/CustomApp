package com.ahmed.customapp.Calulator;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class TextWatcherCalculator implements TextWatcher {

    private EditText editText;

    public TextWatcherCalculator(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0 && String.valueOf(s).matches("^(0*[1-9][0-9]*(\\.[0-9]+)?|0+\\.[0-9]*[1-9][0-9]*)$")){
            Log.e("Watcher","number");
        }else {

            Log.e("Watcher","not number");
        }
    }
}
