package com.ahmed.customapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import jp.wasabeef.glide.transformations.BlurTransformation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.ahmed.customapp.Calulator.CalculatorActivity;
import com.ahmed.customapp.QuranKareem.Splash;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MyAppsActivity extends BaseActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener{

    private Button mButton;
    private ViewPager mViewPager;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private MyAppsPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    private boolean mShowingFragments = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_apps);
        BitmapTransformation bitmapTransformation;



        ImageView imageView = findViewById(R.id.background);
     /*   Glide.with(this).load()
                .apply()
                .into((imageView);

        Glide.with(this)
                .load(getDrawable(R.id.background))
                .transform(new BlurTransformation(this))
                .into(ivBackground);*/

        RequestOptions requestOptions = new  RequestOptions();
        requestOptions.transform(new BlurTransformation(2)); // 0-100
        Glide.with(this).setDefaultRequestOptions(requestOptions)
                .load(getDrawable(R.drawable.newbackground)).into(imageView);



        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mButton = (Button) findViewById(R.id.cardTypeBtn);
        ((CheckBox) findViewById(R.id.checkBox)).setOnCheckedChangeListener(this);
        mButton.setOnClickListener(this);

        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1,R.drawable.splash));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1,R.drawable.splash));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1,R.drawable.splash));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1,R.drawable.splash));

        List<AppCardFragment> appCardFragments = new ArrayList<>();
        List<Integer> imgList = new ArrayList<>();
        imgList.add(R.drawable.splash);
        imgList.add(R.drawable.quran0);
        imgList.add(R.drawable.quran1);
        imgList.add(R.drawable.quran2);
        imgList.add(R.drawable.quran3);
        AppCardFragment f0 = new AppCardFragment();
        f0.setImgList(imgList);
        f0.setAppTitle_str(R.string.quran_kareem);
        AppCardFragment f1 = new AppCardFragment();
        f1.setImgList(imgList);
        f1.setAppTitle_str(R.string.basic_calculator);
        AppCardFragment f2 = new AppCardFragment();
        f2.setImgList(imgList);
        f2.setAppTitle_str(R.string.quran_kareem);
        appCardFragments.add(f0);
        appCardFragments.add(f1);
        appCardFragments.add(f2);

        mFragmentCardAdapter = new MyAppsPagerAdapter(getSupportFragmentManager(),
                dpToPixels(2, this) ,getMyAppsFragments(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onClick(View view) {
        if (!mShowingFragments) {
            mButton.setText("Views");
            mViewPager.setAdapter(mFragmentCardAdapter);
            mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        } else {
            mButton.setText("Fragments");
            mViewPager.setAdapter(mCardAdapter);
            mViewPager.setPageTransformer(false, mCardShadowTransformer);
        }

        mShowingFragments = !mShowingFragments;
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mCardShadowTransformer.enableScaling(b);
        mFragmentCardShadowTransformer.enableScaling(b);
    }


    private List<AppCardFragment> getMyAppsFragments(){
        List<AppCardFragment> appCardFragments = new ArrayList<>();
        appCardFragments.add(getQuranApp());
        appCardFragments.add(getCalculatorApp());

        return appCardFragments;
    }

    private AppCardFragment getQuranApp(){
        AppCardFragment quranApp = new AppCardFragment();
        quranApp.setImgList(getQuranImageList());
        quranApp.setAppTitle_str(R.string.quran_kareem);
        quranApp.setBtn_str(R.string.open_quran);
        openApp(quranApp);
        //quranApp.setAppDescription_Str();
        return quranApp;
    }

    private List<Integer> getQuranImageList(){
        List<Integer> imgList = new ArrayList<>();
        imgList.add(R.drawable.splash);
        imgList.add(R.drawable.quran0);
        imgList.add(R.drawable.quran1);
        imgList.add(R.drawable.quran2);
        imgList.add(R.drawable.quran3);

        return imgList;
    }



    private AppCardFragment getCalculatorApp(){
        AppCardFragment calculatorApp = new AppCardFragment();
        calculatorApp.setImgList(getCalculatorImageList());
        calculatorApp.setAppTitle_str(R.string.basic_calculator);
        calculatorApp.setAppDescription_Str(R.string.calculator_description);
        calculatorApp.setBtn_str(R.string.open_calculator);
        openApp(calculatorApp);
        return calculatorApp;
    }
    private List<Integer> getCalculatorImageList(){
        List<Integer> imgList = new ArrayList<>();
        imgList.add(R.drawable.calculator1);
        return imgList;
    }

    private void openQuran(){
        Intent intent = new Intent(this, Splash.class);
        startActivity(intent);
    }

    private void openCalculator(){
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    private void openApp(AppCardFragment appCardFragment){
        appCardFragment.setCardFragmentOnClickListener(new AppCardFragment.CardFragmentOnClickListener() {
            @Override
            public void onClick(int title) {
                switch (title){
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
}
