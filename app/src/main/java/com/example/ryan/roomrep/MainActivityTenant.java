package com.example.ryan.roomrep;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.LoginActivities.LoginActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivityTenant extends AppCompatActivity implements FragmentEventListener {


    BottomNavigationView bottomMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ProgressDialog progressDialog;

    Tenant tenant;

    //private List<Repair> repairs;
    Toolbar toolbar;
    public String chatPeopleName = "Ziheng He";
    public String chatRoomNameInMainActivityTenant = "TheRegularOne";
    public String chatRoomType = "Test";
    //This String is an example when login activity passed data to MainActivityTenant




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
        bottomMenu.setOnNavigationItemSelectedListener(onBottomMenu);


        navigationView.setNavigationItemSelectedListener(onNavigationMenu);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        handleIntent(getIntent());

        Bundle bundle = getIntent().getExtras();

        tenant = bundle.getParcelable("TENANT_DATA");



        if (savedInstanceState == null) {
            bottomMenu.getMenu().getItem(1).setChecked(true);
        }

    }


    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String appLinkAction = intent.getAction();
        Uri appLinkData = intent.getData();
        if (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null){

        }
    }


    private NavigationView.OnNavigationItemSelectedListener onNavigationMenu = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.lblRent:
                    break;
                case R.id.lblRepair:

                    break;
                case R.id.lblLogout:
                    Intent intent = new Intent(MainActivityTenant.this, LoginActivity.class);
                    startActivity(intent);
            }
            drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        }
    };


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }


    }

    public void addToSharedPreferences(Tenant tenant) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("TenantFirstName", tenant.getFirstName());
        editor.putString("TenantLastName", tenant.getLastName());
        editor.putString("TenantTenantEmail", tenant.getTenantEmail());
        editor.putString("TenantLandlordEmail", tenant.getLandlordEmail());
        editor.putString("TenantPassword", tenant.getPassword());
        editor.putString("TenantPassword2", tenant.getPassword2());
        editor.apply();
    }





    private BottomNavigationView.OnNavigationItemSelectedListener onBottomMenu = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navMessagR:
                    //router.onNavigateToMessages();
                    item.setChecked(true);
                    break;
                case R.id.lblHome:
                    item.setChecked(true);
                    break;
                case R.id.navRating:
                    //router.onNavigateToRatingLandlord(house.getLandlordEmail());
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
        JSONObject jsonObject = convertStringToJSONObject(response);
        Gson gson = new Gson();



    }

    private JSONObject convertStringToJSONObject(String response) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }







}








