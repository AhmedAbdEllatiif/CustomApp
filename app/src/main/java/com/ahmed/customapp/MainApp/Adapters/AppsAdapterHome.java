package com.ahmed.customapp.MainApp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmed.customapp.MainApp.Models.MyAppsModel;
import com.ahmed.customapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;





public class AppsAdapterHome extends RecyclerView.Adapter<AppsAdapterHome.ViewHolder> {

    private Context context;
    private List<MyAppsModel> myAppsModels;

    public AppsAdapterHome(Context context, List<MyAppsModel> myAppsModels) {
        this.context = context;
        this.myAppsModels = myAppsModels;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_apps_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyAppsModel appModel = myAppsModels.get(position);
        holder.app_image.setImageResource(appModel.getApp_image());
        holder.txt_appTitle.setText(appModel.getApp_name());

    }

    @Override
    public int getItemCount() {
        return myAppsModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView app_image;
        TextView txt_appTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            app_image = itemView.findViewById(R.id.app_image);
            txt_appTitle = itemView.findViewById(R.id.txt_appTitle);


        }
    }
}
