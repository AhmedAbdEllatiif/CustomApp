package com.ahmed.customapp.QuranKareem.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmed.customapp.QuranKareem.QuranApi.Models.RadioItem;
import com.ahmed.customapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.ViewHolder> {

    private List<RadioItem> radioItems;


    public RadioAdapter(List<RadioItem> radioItems) {
        this.radioItems = radioItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtRadioChannel;

        public ViewHolder(View itemView) {
            super(itemView);

            txtRadioChannel = itemView.findViewById(R.id.txtRadioChannel);

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_radio,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RadioItem item = radioItems.get(position);

        holder.txtRadioChannel.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return radioItems.size();
    }


}

