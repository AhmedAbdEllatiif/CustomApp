package com.ahmed.customapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ahmed.customapp.MainApp.Adapters.Decoration.CirclePagerIndicatorDecoration;
import com.ahmed.customapp.MainApp.Adapters.MyAppsAdapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AppCardFragment extends Fragment {

    //Views
    private View mainView;
    private TextView txt_appTitle;
    private TextView app_Description;
    private CardView mCardView;
    private RecyclerView recyclerView;
    private Button btn_showApp;



    private List<Integer> imgList;
    private Integer appTitle_str;
    private Integer appDescription_Str;
    private Integer btn_str;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.card_app_fragment, container, false);
        mCardView = (CardView) mainView.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * PagerAdapterHelper.MAX_ELEVATION_FACTOR);

        initViews();

        setupRecyclerView();

        addTitleToTextView();

        addDescriptionToTextView();

        setBtnText();

        onViewClicked();

        return mainView;
    }

    private void initViews(){
        recyclerView = mainView.findViewById(R.id.app_img_recyclerView);
        txt_appTitle = mainView.findViewById(R.id.txt_appTitle);
        app_Description = mainView.findViewById(R.id.app_Description);
        btn_showApp = mainView.findViewById(R.id.btn_showApp);

    }

    private void onViewClicked(){
        btn_showApp.setOnClickListener(v -> {
            if (cardFragmentOnClickListener != null){
                if (appTitle_str != null){
                    cardFragmentOnClickListener.onClick(appTitle_str);
                }
            }
        });
    }

    private void setupRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        MyAppsAdapter adapter = new MyAppsAdapter(getContext(),imgList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new CirclePagerIndicatorDecoration(getActivity(), recyclerView));
    }

    private void addTitleToTextView(){
        if (appTitle_str != null){
            txt_appTitle.setText(appTitle_str);
        }
    }

    private void addDescriptionToTextView(){
        if (appDescription_Str != null){
            app_Description.setText(appDescription_Str);
        }
    }

    public void setImgList(List<Integer> imgList) {
        this.imgList = imgList;
    }

    public void setAppTitle_str(Integer appTitle_str) {
        this.appTitle_str = appTitle_str;
    }

    public void setAppDescription_Str(Integer appDescription_Str) {
        this.appDescription_Str = appDescription_Str;
    }

    public CardView getCardView() {
        return mCardView;
    }

    private void setBtnText(){
        if (btn_str != null){
            btn_showApp.setText(btn_str);
        }
    }


    public void setBtn_str(Integer btn_str) {
        this.btn_str = btn_str;
    }

    private CardFragmentOnClickListener cardFragmentOnClickListener;

    public void setCardFragmentOnClickListener(CardFragmentOnClickListener cardFragmentOnClickListener) {
        this.cardFragmentOnClickListener = cardFragmentOnClickListener;
    }

    interface CardFragmentOnClickListener{
        void onClick(int title);
    }
}
