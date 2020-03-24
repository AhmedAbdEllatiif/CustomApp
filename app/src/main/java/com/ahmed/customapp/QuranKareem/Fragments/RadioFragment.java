package com.ahmed.customapp.QuranKareem.Fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.ahmed.customapp.BaseClasses.BaseFragment;
import com.ahmed.customapp.QuranKareem.Adapters.RadioAdapter;
import com.ahmed.customapp.QuranKareem.FragmentsContainer;
import com.ahmed.customapp.QuranKareem.QuranApi.ApiManger;
import com.ahmed.customapp.QuranKareem.QuranApi.Models.RadioItem;
import com.ahmed.customapp.QuranKareem.QuranApi.Models.RadioResponse;
import com.ahmed.customapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RadioFragment extends BaseFragment {


    private View view;
    private List<RadioItem> radioChannels = new ArrayList<>();
    private RadioAdapter adapter;
    private RecyclerView radio_recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SnapHelper snapHelper;
    private ImageButton imgBtnPlay;
    private ImageButton imgBtnStop;
    private MediaPlayer mediaPlayer;


    public RadioFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_radio, container, false);
        FragmentsContainer.my_title.setText(R.string.radio);

        imgBtnPlay = view.findViewById(R.id.imgBtnPlay);
        imgBtnStop = view.findViewById(R.id.imgBtnStop);

        getRadioChannels();

        return view;
    }

    private void stopMediaPlayer(){
        if(mediaPlayer !=null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    private void playMediaPlayer(String URL){
        stopMediaPlayer();
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(URL);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void setAdapter(){

        linearLayoutManager =
                new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false);
        adapter = new RadioAdapter(radioChannels);
        radio_recyclerView = view.findViewById(R.id.radio_recyclerView);
        radio_recyclerView.setLayoutManager(linearLayoutManager);
        radio_recyclerView.setAdapter(adapter);
        snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(radio_recyclerView);

        imgBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = snapHelper.findSnapView(linearLayoutManager);
                int position = radio_recyclerView.getChildAdapterPosition(view);
                playMediaPlayer(radioChannels.get(position).getURL());

            }
        });


        imgBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMediaPlayer();
            }
        });

    }

    private void getRadioChannels(){

        ApiManger.getServices().getRadioChannels()
                .enqueue(new Callback<RadioResponse>() {
                    @Override
                    public void onResponse(Call<RadioResponse> call, Response<RadioResponse> response) {

                        radioChannels = response.body().getRadioItems();
                        setAdapter();
                    }

                    @Override
                    public void onFailure(Call<RadioResponse> call, Throwable t) {

                        showMessage("Error", t.getLocalizedMessage());
                    }
                });

    }


}