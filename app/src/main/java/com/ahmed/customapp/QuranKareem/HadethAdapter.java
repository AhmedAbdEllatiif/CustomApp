package com.ahmed.customapp.QuranKareem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmed.customapp.QuranKareem.QuranModels.HadethModel;
import com.ahmed.customapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class HadethAdapter extends RecyclerView.Adapter<HadethAdapter.ViewHolder> {

    ArrayList<HadethModel> itemViews;
    private Context context;
    onItemClickListener onCardClickListener;



    public HadethAdapter(ArrayList<HadethModel> itemViews, Context context) {
        this.itemViews = itemViews;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtHadethTitle;
        CardView hadeth_cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            txtHadethTitle = itemView.findViewById(R.id.txtHadethTitle);
            hadeth_cardView = itemView.findViewById(R.id.hadeth_cardView);
        }
    }

    public void setOnCardClickListener (onItemClickListener onCardClickListener){
        this.onCardClickListener = onCardClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_hadeth,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final HadethModel itemView = itemViews.get(position);

        holder.txtHadethTitle.setText(itemView.getTitle());

        if (onCardClickListener != null){

            holder.hadeth_cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCardClickListener.onClick(position,itemView);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return itemViews.size();
    }


    public interface onItemClickListener{

        void onClick(int position, HadethModel itemView);
    }

}
