package com.ahmed.customapp.QuranKareem.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmed.customapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class HadethDilaogFramgent extends DialogFragment {

    private String title, content;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HadethDilaogFramgent() {
    }


    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ahadeth_dialog_fragment,container,false);

        TextView txtContent = view.findViewById(R.id.txtContent);
        TextView txtDialogTitle = view.findViewById(R.id.txtDialogTitle);

        txtDialogTitle.setText(title);
        txtContent.setText(content);

        return view;
    }
}