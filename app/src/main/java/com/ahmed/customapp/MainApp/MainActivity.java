package com.ahmed.customapp.MainApp;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ahmed.customapp.BaseClasses.BaseActivity;
import com.ahmed.customapp.R;
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
}
