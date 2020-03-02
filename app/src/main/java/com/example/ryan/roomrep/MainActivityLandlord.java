package com.example.ryan.roomrep;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;

import com.example.ryan.roomrep.LandlordFragments.AddHouseFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ryan.roomrep.Classes.Iterator.JSONArrayIterator;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.LoginActivities.LoginActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivityLandlord extends AppCompatActivity implements FragmentEventListener
{
    BottomNavigationView bottomMenu;
    DrawerLayout drawerLayout;

    Toolbar myToolbar;
    Landlord landlord;


    AppBarConfiguration appBarConfiguration;
    NavController navController;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landlord);
        myToolbar = findViewById(R.id.toolbarLandlord);
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        drawerLayout = findViewById(R.id.landlord_drawer_layout);
        bottomMenu = findViewById(R.id.navBarLandlord);




        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
         //   landlord = new Landlord("Ryan", "Sneyd", "aaaaaa", "aaaaaa", "a@s.com");
        }
        else {
            landlord = bundle.getParcelable("LANDLORD_DATA");

        }


        bottomMenu.setOnNavigationItemSelectedListener(onBottomMenu);

        setSupportActionBar(myToolbar);



        navController = Navigation.findNavController(this, R.id.nav_landlord_host_fragment);

        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomMenu, navController);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (savedInstanceState == null) {
            bottomMenu.getMenu().getItem(1).setChecked(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_landlord_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if(!Navigation.findNavController(this, R.id.nav_landlord_host_fragment).popBackStack()){
            finish();
        }



    }





    private BottomNavigationView.OnNavigationItemSelectedListener onBottomMenu = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navRepairTrackR:
                    //getRepairs();
                    item.setChecked(true);

                    break;
                case R.id.navHouses:
                    item.setChecked(true);
                    break;
                case R.id.navNotifyR:

                    item.setChecked(true);
                    break;
                default:
                    return false;
            }
            return false;
        }
    };


    @Override
    public void update(String response) {

        progressDialog.dismiss();


    }


    private JSONArray convertStringToJSONArray(String response) {
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(response);
            return jsonArray;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
