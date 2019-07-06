package com.example.ryan.roomrep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ryan.roomrep.Adapters.StatePagerAdapter;
import com.example.ryan.roomrep.Classes.House;
import com.example.ryan.roomrep.Classes.Landlord;
import com.example.ryan.roomrep.Classes.Repair;

import com.example.ryan.roomrep.LoginActivities.LoginActivity;
import com.example.ryan.roomrep.TenantFragments.AddGroup;
import com.example.ryan.roomrep.TenantFragments.ListTargetChatUserFragment;
import com.example.ryan.roomrep.TenantFragments.MessagRFragment;
import com.example.ryan.roomrep.TenantFragments.MessageLandlord;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.prefs.Preferences;

public class MainActivityLandlord extends AppCompatActivity  {


    BottomNavigationView bottomMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar myToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landlord);
        myToolbar = findViewById(R.id.toolbarLandlord);
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomMenu = findViewById(R.id.navBarLandlord);
        navigationView = findViewById(R.id.nav_view);





        navigationView.setNavigationItemSelectedListener(onNavigationMenu);
        bottomMenu.setOnNavigationItemSelectedListener(onBottomMenu);
        setSupportActionBar(myToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, myToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){

        }


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();

    }



    private NavigationView.OnNavigationItemSelectedListener onNavigationMenu = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.nav_listings:
                    Toast.makeText(MainActivityLandlord.this, "My Listings", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_settings:
                    Toast.makeText(MainActivityLandlord.this, "Settings", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.nav_logout:
                    Toast.makeText(MainActivityLandlord.this, "Logout", Toast.LENGTH_SHORT).show();

            }
            drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener onBottomMenu = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navRepairTrackR:
                    break;
                case R.id.navHouses:
                    break;
                case R.id.navNotifyR:

                    break;
                default:
                    return false;
            }
            return false;
        }
    };





}
