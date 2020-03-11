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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SouraListAdapter extends RecyclerView.Adapter<SouraListAdapter.ViewHolder> {
    private List<SouraModel> souraModels;
    private Context context;
    private onItemClickListener onItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SouraListAdapter(Context context, List<SouraModel> souraModels) {
        this.souraModels = souraModels;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        CardView cardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_souraname_, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        final SouraModel souraModel = souraModels.get(position);
        holder.txtName.setText(souraModel.getName());

        if(onItemClickListener != null){
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(position, souraModel);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return souraModels.size();
    }


    public interface onItemClickListener{

        void onClick(int position, SouraModel souraModel);

    }

}