package com.example.ryan.roomrep;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.TenantRouter;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.LoginActivities.LoginActivity;
import com.example.ryan.roomrep.TenantFragments.CompleteRentFragment;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivityTenant extends AppCompatActivity implements FragmentEventListener {


    BottomNavigationView bottomMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TenantRouter router;
    ProgressDialog progressDialog;

    Tenant tenant;

    //private List<Repair> repairs;
    Toolbar toolbar;
    House house;
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
        if (router == null) {
            router = new TenantRouter(getSupportFragmentManager(), new ArrayList<Repair>());
            chatPeopleName = tenant.getFirstName() + " " + tenant.getLastName();
            //chatRoomNameInMainActivityTenant = house.getAddress();
            //chatRoomNameInMainActivityTenant = tenant.getHouseAddress();
            if (chatRoomNameInMainActivityTenant == null) {
                chatPeopleName = "Ryan Sneyd";
            }


            addToSharedPreferences(tenant);
            Network network = Network.getInstance();
            network.registerObserver(this);
            network.getTenantHouse(tenant);
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading house data...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }


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
            router = new TenantRouter(getSupportFragmentManager(), new ArrayList<Repair>());
            router.onNavigateToCompleteRent();
        }
    }


    private NavigationView.OnNavigationItemSelectedListener onNavigationMenu = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.lblRent:
                    router.onNavigateToPayRent(tenant);
                    break;
                case R.id.lblRepair:
                    router.onNavigateToTenantRepairsList();
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


        router.popBackStack();
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

    public void setTenantAndHouse(Tenant tenant, House house) {
        this.house = house;
        this.tenant = tenant;
        if (router == null) {
            router = new TenantRouter(getSupportFragmentManager(), new ArrayList<Repair>());
            router.setTenantAndHouse(tenant, house);
            return;
        }
        router.setTenantAndHouse(tenant, house);
    }



    private BottomNavigationView.OnNavigationItemSelectedListener onBottomMenu = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navMessagR:
                    //router.onNavigateToMessages();
                    router.onNavigateToMessagesPeopleList(tenant,house);
                    item.setChecked(true);
                    break;
                case R.id.lblHome:
                    router.onNavigateToTenantLanding(house, tenant);
                    item.setChecked(true);
                    break;
                case R.id.navRating:
                    //router.onNavigateToRatingLandlord(house.getLandlordEmail());
                    router.onNavigateToSlottingLandlord(tenant.getLandlordEmail());
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
        house = gson.fromJson(jsonObject.toString(), House.class);

        router.onNavigateToTenantLanding(house, tenant);


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








