package com.example.ryan.roomrep;

import android.app.ProgressDialog;
import android.content.Intent;
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

import com.example.ryan.roomrep.Adapters.HouseRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Iterator.JSONArrayIterator;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.Classes.Rent.Payment;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.LandlordRouter;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.LandlordFragments.HousesFragment;
import com.example.ryan.roomrep.LoginActivities.LoginActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivityLandlord extends AppCompatActivity implements FragmentEventListener
{
    BottomNavigationView bottomMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar myToolbar;
    LandlordRouter router;
    Landlord landlord;


    public Tenant peopleToAdd;
    public String chatPeopleName = "Ziheng He";
    public String chatRoomNameInMainActivityLandlord = "TheRegularOne";
    public String chatRoomType = "Test";
    public List<Profile> mainProfiles;
    public List<Tenant>mainTenants = new ArrayList<>();

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landlord);
        myToolbar = findViewById(R.id.toolbarLandlord);
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomMenu = findViewById(R.id.navBarLandlord);
        navigationView = findViewById(R.id.nav_view);



        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            landlord = new Landlord("Ryan", "Sneyd", "aaaaaa", "aaaaaa", "a@s.com");
        }
        else {
            landlord = bundle.getParcelable("LANDLORD_DATA");
            chatPeopleName = landlord.getFirstName()+ " "+landlord.getLastName();
        }



        navigationView.setNavigationItemSelectedListener(onNavigationMenu);
        bottomMenu.setOnNavigationItemSelectedListener(onBottomMenu);
        setSupportActionBar(myToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, myToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Network network = Network.getInstance();
        network.registerObserver(this);
        network.getLandlordHouses(landlord);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting houses...");

        progressDialog.show();

        if (savedInstanceState == null) {
            bottomMenu.getMenu().getItem(1).setChecked(true);
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
                    router.onNavigateToLandlordListings(landlord);
                    break;
                case R.id.nav_logout:
                    Intent intent = new Intent(MainActivityLandlord.this, LoginActivity.class);
                    startActivity(intent);
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
                    //getRepairs();
                    item.setChecked(true);
                    router.onNavigateToLandlordRepairs();
                    break;
                case R.id.navHouses:
                    router.onNavigateToHouses(landlord);
                    item.setChecked(true);
                    break;
                case R.id.navNotifyR:
                    router.onNavigateToNotifyR();
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
        final List<House> networkHouses = new ArrayList<>();
        JSONArray jsonArray = convertStringToJSONArray(response);
        Gson gson = new Gson();
        Iterator iterator = new JSONArrayIterator(jsonArray);
        while (iterator.hasNext()){
            House house = gson.fromJson(iterator.next().toString(), House.class);

            networkHouses.add(house);
        }
        progressDialog.dismiss();
        router = new LandlordRouter(getSupportFragmentManager(), networkHouses);
        router.onNavigateToHouses(landlord);
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
