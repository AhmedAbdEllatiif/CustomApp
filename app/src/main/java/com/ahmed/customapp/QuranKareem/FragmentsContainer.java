package com.ahmed.customapp.QuranKareem;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.ahmed.customapp.BaseActivity;
import com.ahmed.customapp.QuranKareem.Fragments.HadeathFragment;
import com.ahmed.customapp.QuranKareem.Fragments.QuranFragment;
import com.ahmed.customapp.QuranKareem.Fragments.RadioFragment;
import com.ahmed.customapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class FragmentsContainer extends BaseActivity {


    public static TextView my_title;
    private Toolbar toolbar;
    private Fragment fragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.quran:
                    fragment = new QuranFragment();
                    break;
                case R.id.ahadeath:
                    fragment = new HadeathFragment();
                    break;
                case R.id.radio:
                    fragment = new RadioFragment();
                    break;
            }

            Log.e("Fragment", "Replacing Fragment");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer,fragment)
                    .commit();
            return true;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_);



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.quran);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar()
                .setDisplayShowTitleEnabled(false);

        my_title = (TextView) findViewById(R.id.my_title) ;
        my_title.setText(R.string.quran);


    }

}