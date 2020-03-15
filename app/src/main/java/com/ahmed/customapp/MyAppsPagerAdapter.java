package com.ahmed.customapp;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyAppsPagerAdapter extends FragmentStatePagerAdapter implements PagerAdapterHelper {

    private List<AppCardFragment> mFragments;
    private float mBaseElevation;

    public MyAppsPagerAdapter(@NonNull FragmentManager fm, float baseElevation,List<AppCardFragment> mFragments,int behavior) {
        super(fm, behavior);

        this.mFragments = mFragments;
        mBaseElevation = baseElevation;

        /*for(int i = 0; i< 5; i++){
            addCardFragment(new AppCardFragment());
        }*/
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }




    /**
     * Call backs {@link PagerAdapterHelper}
     */
    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mFragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        mFragments.set(position, (AppCardFragment) fragment);
        return fragment;
    }

    public void addCardFragment(AppCardFragment fragment) {
        mFragments.add(fragment);
    }
}
