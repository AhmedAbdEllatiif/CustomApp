package com.ahmed.customapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.ahmed.customapp.BaseClasses.BaseActivity;
import com.ahmed.customapp.Calulator.CalculatorActivity;
import com.ahmed.customapp.MainApp.Helpers.MyAppsFragmentsHelper;
import com.ahmed.customapp.QuranKareem.OuranSplash;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.util.ArrayList;
import java.util.List;

public class MyAppsActivity extends BaseActivity implements
        CompoundButton.OnCheckedChangeListener {

    private Button mButton;
    private MyViewPager mViewPager;
    private CheckBox checkBox;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private MyAppsPagerAdapter appsPagerAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    private boolean mShowingFragments = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_apps);
        BitmapTransformation bitmapTransformation;
        mViewPager = (MyViewPager) findViewById(R.id.viewPager);


        ImageView imageView = findViewById(R.id.background);
       /* RequestOptions requestOptions = new RequestOptions();
        requestOptions.transform(new BlurTransformation(10)); // 0-100
        Glide.with(this).setDefaultRequestOptions(requestOptions)
                .load(getDrawable(R.drawable.back1)).into(imageView);*/



        //mButton = (Button) findViewById(R.id.cardTypeBtn);
       //((CheckBox) findViewById(R.id.checkBox)).setOnCheckedChangeListener(this);

        //mButton.setOnClickListener(this);
        checkBox = findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(this);
        checkBox.setChecked(true);

        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1,R.drawable.splash));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1,R.drawable.splash));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1,R.drawable.splash));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1,R.drawable.splash));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        setupAppsPagerAdapter();
    }

    /**
     * To setup the viewPager with the MyAppsAdapter
     * */
    private void setupAppsPagerAdapter() {
     /*   appsPagerAdapter = new MyAppsPagerAdapter(getSupportFragmentManager(),
                getElevation(50, this), getMyAppsFragments(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, appsPagerAdapter);
        //mViewPager.setAdapter(appsPagerAdapter);
        //mViewPager.setPageTransformer(false, mCardShadowTransformer);
        //mViewPager.setOffscreenPageLimit(3);

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);

        mViewPager.setOffscreenPageLimit(3);*/
        //mCardShadowTransformer.enableScaling(true);
        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mCardShadowTransformer.enableScaling(true);
    }

    /**
     * Convert dp into pixels to set the base elevation of the fragments
     * */
    public static float getElevation(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    /**
     * To open Quran Application
     * */
    private void openQuran() {
        Intent intent = new Intent(this, OuranSplash.class);
        startActivity(intent);
    }

    /**
     * To open Calculator Application
     * */
    private void openCalculator() {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    /**
     * To choose which app to open when button pressed
     * */
    private void chooseAppToOpen(AppCardFragment appCardFragment) {
        appCardFragment.setCardFragmentOnClickListener(new AppCardFragment.CardFragmentOnClickListener() {
            @Override
            public void onClick(int title) {
                switch (title) {
                    case R.string.quran_kareem:
                        openQuran();
                        break;
                    case R.string.basic_calculator:
                        openCalculator();
                        break;
                }
            }
        });
    }


   /* @Override
    public void onClick(View view) {
        if (!mShowingFragments) {
            mButton.setText("Views");
            mViewPager.setAdapter(appsPagerAdapter);
            mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        } else {
           *//* mButton.setText("Fragments");
            mViewPager.setAdapter(mCardAdapter);
            mViewPager.setPageTransformer(false, mCardShadowTransformer);*//*
        }

        mShowingFragments = !mShowingFragments;
    }*/

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        // mCardShadowTransformer.enableScaling(b);
        //mFragmentCardShadowTransformer.enableScaling(b);
    }


    private List<AppCardFragment> getMyAppsFragments() {
        List<AppCardFragment> appCardFragments = new ArrayList<>();
        appCardFragments.add(getQuranApp());
        appCardFragments.add(getCalculatorApp());

        return appCardFragments;
    }

    /**
     * To get fragment with Quran app details
     * */
    private AppCardFragment getQuranApp() {
        AppCardFragment quranApp = new AppCardFragment();
        quranApp.setImgList(MyAppsFragmentsHelper.getQuranImageList());
        quranApp.setAppTitle_str(R.string.quran_kareem);
        quranApp.setBtn_str(R.string.open_quran);
        chooseAppToOpen(quranApp);
        //quranApp.setAppDescription_Str();
        return quranApp;
    }


    /**
     * To get fragment with calculator app details
     * */
    private AppCardFragment getCalculatorApp() {
        AppCardFragment calculatorApp = new AppCardFragment();
        calculatorApp.setImgList(MyAppsFragmentsHelper.getCalculatorImageList());
        calculatorApp.setAppTitle_str(R.string.basic_calculator);
        calculatorApp.setAppDescription_Str(R.string.calculator_description);
        calculatorApp.setBtn_str(R.string.open_calculator);
        chooseAppToOpen(calculatorApp);
        return calculatorApp;
    }



}
