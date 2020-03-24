package com.ahmed.customapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.widget.ImageView;

import com.ahmed.customapp.MainApp.Adapters.AppsAdapterHome;
import com.ahmed.customapp.MainApp.Models.MyAppsModel;

import java.util.ArrayList;
import java.util.List;

public class WeActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private AppsAdapterHome adapter;
    private  LinearLayoutManager linearLayoutManager;
    private ImageView img_next;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we);

        initViews();

        setupRecyclerView();

        onViewsClicked();

    }

    private void initViews(){
        recyclerView = findViewById(R.id.apps_recyclerView_home);
        img_next = findViewById(R.id.img_next);
        img_back = findViewById(R.id.img_back);
    }


    private void onViewsClicked(){
        img_next.setOnClickListener(v ->{
            if (adapter != null && linearLayoutManager != null){
                int lastItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastItem != linearLayoutManager.getItemCount() - 1){
                recyclerView.smoothScrollToPosition(linearLayoutManager.findFirstCompletelyVisibleItemPosition() + 1);
                }
            }
        });

        img_back.setOnClickListener(v ->{
            if (adapter != null && linearLayoutManager != null){
                int firstItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                if (firstItem != 0){
                    recyclerView.smoothScrollToPosition(linearLayoutManager.findFirstCompletelyVisibleItemPosition() - 1);
                }
            }
        });
    }

    private void setupRecyclerView(){
        adapter = new AppsAdapterHome(this,getAppsModel());
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setAdapter(adapter);
        snapHelper.attachToRecyclerView(recyclerView);

    }


    private List<MyAppsModel> getAppsModel(){
        List<MyAppsModel> modelList = new ArrayList<>();
        modelList.add(new MyAppsModel(R.drawable.quran0,getString(R.string.quran_kareem)));
        modelList.add(new MyAppsModel(R.drawable.calculator1,getString(R.string.basic_calculator)));
        modelList.add(new MyAppsModel(R.drawable.quran0,getString(R.string.quran_kareem)));
        modelList.add(new MyAppsModel(R.drawable.calculator1,getString(R.string.basic_calculator)));
        return modelList;
    }
}
