package com.example.ryan.roomrep.Activities;

import com.example.ryan.roomrep.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.example.ryan.roomrep.Classes.Network.NetworkObserver;

public class MainActivityTenant extends AppCompatActivity implements NetworkObserver {


    BottomNavigationView bottomMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tenant);
        toolbar = findViewById(R.id.tenantToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = findViewById(R.id.tenant_drawer_layout);
        navigationView = findViewById(R.id.tenant_nav_view);
        bottomMenu = findViewById(R.id.navBar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void update(String response) {

    }








}








