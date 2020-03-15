package com.ahmed.customapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ahmed.customapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAppsAdapter extends RecyclerView.Adapter<MyAppsAdapter.ViewHolder> {

    private Context context;
    private List<Integer> imgList;


    public MyAppsAdapter(Context context, List<Integer> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_app_image,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int appImage = imgList.get(position);
            holder.myApp_image.setBackgroundResource(appImage);
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView myApp_image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            myApp_image = itemView.findViewById(R.id.myApp_image);
        }



    }
}
