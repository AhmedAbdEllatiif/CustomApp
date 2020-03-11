package com.ahmed.customapp.QuranKareem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmed.customapp.QuranKareem.QuranModels.SouraModel;
import com.ahmed.customapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SouraAdapter extends RecyclerView.Adapter<SouraAdapter.ViewHolder> {
    private List<SouraModel> souraModels;
    private Context context;


    public SouraAdapter(Context context, List<SouraModel> souraModels) {
        this.souraModels = souraModels;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtAyah;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAyah = itemView.findViewById(R.id.txtAyah);

        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_ayah,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        final SouraModel souraModel = souraModels.get(position);
        holder.txtAyah.setText(souraModel.getAyah());



    }

    @Override
    public int getItemCount() {
        return souraModels.size();
    }

//ViewHolder


}