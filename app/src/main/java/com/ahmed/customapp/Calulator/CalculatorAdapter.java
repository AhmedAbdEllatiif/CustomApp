package com.ahmed.customapp.Calulator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ahmed.customapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalculatorAdapter extends RecyclerView.Adapter<CalculatorAdapter.ViewHolder> {


    private Context context;
    private ArrayList<Calc_ItemList> itemLists;

    private static final int ORANGE_BTN = 1;
    private static final int DARK_GRAY_BTN = 2;

    public CalculatorAdapter(Context context, ArrayList<Calc_ItemList> itemLists) {
        this.context = context;
        this.itemLists = itemLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case ORANGE_BTN : view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orange_btn_calc,parent,false);
            break;
            case DARK_GRAY_BTN : view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dark_gray_btn_calc,parent,false);
                break;
            default : view = LayoutInflater.from(parent.getContext()).inflate(R.layout.light_gray_btn_calc,parent,false);
        }



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Calc_ItemList itemList = itemLists.get(position);

        holder.button.setText(itemList.getValue());
        if (position == 0){
            holder.button.setTextColor(context.getColor(R.color.red));
        }

        if (myOnClickLister != null){
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myOnClickLister.onBtnClicked(itemList,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if(itemLists != null) return itemLists.size();
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        int lastItem = itemLists.size() - 1;
        int mod = ( position + 1) % 4;
        if (mod == 0 ) return ORANGE_BTN;
        if (position == lastItem) return  ORANGE_BTN;
        if (position > 3) return DARK_GRAY_BTN;
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.calc_btn);

        }
    }

    private MyOnClickLister myOnClickLister;

    public void setMyOnClickLister(MyOnClickLister myOnClickLister) {
        this.myOnClickLister = myOnClickLister;
    }

    interface MyOnClickLister{
        void onBtnClicked(Calc_ItemList calc_itemList,int position);
    }


}
