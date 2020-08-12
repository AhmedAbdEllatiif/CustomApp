package com.ahmed.customapp.MainApp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ahmed.customapp.BaseClasses.BaseActivity;
import com.ahmed.customapp.MyAppsActivity;
import com.ahmed.customapp.R;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ImageButton img_menu;
    private TextView txt_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to initialize views
        initViews();

        //to handle when views clicked
        onViewsClicked();

        //to setup Drawer
        setupDrawer();

        //to setup NavigationController
        setupNavController();

        simple();

    }

    /**
     * To initialize views
     * */
    private void initViews() {
        img_menu = findViewById(R.id.img_menu);
        txt_title = findViewById(R.id.txt_title);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    /**
     * To handle when views clicked
     * */
    private void onViewsClicked() {
        img_menu.setOnClickListener(v -> {
            if (!drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.openDrawer(GravityCompat.START);
            } else {
                drawer.closeDrawer(GravityCompat.END);
            }
        });
    }


    /**
     * To setup Drawer
     * */
    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private static final String TAG = "MainActivity";

    /**
     * To setup NavigationController
     * */
    private void setupNavController(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                assert destination.getLabel() != null;
                Log.d(TAG, "onDestinationChanged: " + destination.getLabel());
                String title_str = destination.getLabel().toString();
                txt_title.setText(title_str);
            }
        });
    }

    /**
     * Callback {@link NavigationView.OnNavigationItemSelectedListener}
     * To handle when item of navigation menu is selected
     * */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    private void simple(){
        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.img_menu), "This is a target",
                        "We have the best targets, believe me")
                        // All options below are optional
                        .outerCircleColor(R.color.colorPrimaryDark)      // Specify a color for the outer circle
                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
                        .titleTextSize(20)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.white)      // Specify the color of the title text
                        .descriptionTextSize(10)            // Specify the size (in sp) of the description text
                        .descriptionTextColor(R.color.red)  // Specify the color of the description text
                        .textColor(R.color.white)           // Specify a color for both the title and description text
                        .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                        .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)                   // Whether to tint the target view's color
                        .transparentTarget(true)           // Specify whether the target is transparent (displays the content underneath)
                        //.icon(getDrawable(R.drawable.calculator))                     // Specify a custom drawable to draw as the target
                        .targetRadius(60),                  // Specify the target radius (in dp)
                new TapTargetView.Listener() {              // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional
                        Log.e(TAG, "onTargetClick: ");
                        view.dismiss(true);
                        /*Intent intent = new Intent(MainActivity.this, MyAppsActivity.class);
                        startActivity(intent);*/
                    }
                });
    }
}
