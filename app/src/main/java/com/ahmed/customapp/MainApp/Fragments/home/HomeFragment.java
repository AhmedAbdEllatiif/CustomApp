package com.ahmed.customapp.MainApp.Fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.ahmed.customapp.MainApp.Adapters.AppsAdapterHome;
import com.ahmed.customapp.MainApp.Models.MyAppsModel;
import com.ahmed.customapp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private View view;

    private RecyclerView recyclerView;
    private AppsAdapterHome adapter;
    private LinearLayoutManager linearLayoutManager;
    private ImageView img_next;
    private ImageView img_back;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        view = inflater.inflate(R.layout.fragment_home, container, false);


        initViews();

        setupRecyclerView();

        onViewsClicked();

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return view;
    }


    private void initViews(){
        recyclerView = view.findViewById(R.id.apps_recyclerView_home);
        img_next = view.findViewById(R.id.img_next);
        img_back = view.findViewById(R.id.img_back);
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
        adapter = new AppsAdapterHome(getContext(),getAppsModel());
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.setAdapter(adapter);
        snapHelper.attachToRecyclerView(recyclerView);

    }


    private List<MyAppsModel> getAppsModel(){
        List<MyAppsModel> modelList = new ArrayList<>();
        modelList.add(new MyAppsModel(R.drawable.img_zawia,getString(R.string.txt_zawia)));
        modelList.add(new MyAppsModel(R.drawable.quran0,getString(R.string.quran_kareem)));
        modelList.add(new MyAppsModel(R.drawable.calculator,getString(R.string.basic_calculator)));
        modelList.add(new MyAppsModel(R.drawable.quran0,getString(R.string.quran_kareem)));
        modelList.add(new MyAppsModel(R.drawable.calculator,getString(R.string.basic_calculator)));
        return modelList;
    }
}
