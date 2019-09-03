package com.example.ryan.roomrep;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.Router.LandlordRouter;

public class MainActivityLandlord extends AppCompatActivity  {


    BottomNavigationView bottomMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar myToolbar;
    LandlordRouter router;

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

        router = new LandlordRouter(getSupportFragmentManager());
        if (savedInstanceState == null){
            navigationView.setCheckedItem(R.id.nav_listings);
            router.navigateToHouses();
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
