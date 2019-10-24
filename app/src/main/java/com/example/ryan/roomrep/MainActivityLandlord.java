package com.example.ryan.roomrep;

import android.app.ProgressDialog;
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

import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.Classes.Rent.Payment;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.LandlordRouter;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivityLandlord extends AppCompatActivity implements FragmentEventListener
{

    private List<Repair> repairs;
    ProgressDialog progressDialog;

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
            landlord = new Landlord("Ryan", "Sneyd", "aaaaaa", "aaaaaa", "a@s.com", new ArrayList<Payment>());
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

        router = new LandlordRouter(getSupportFragmentManager());
        router.onNavigateToHouses(landlord);

        if (savedInstanceState == null){
            navigationView.setCheckedItem(R.id.nav_listings);

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
                    getRepairs();
                case R.id.navHouses:
                    break;
                case R.id.navNotifyR:
                    router.onNavigateToNotifyR();
                    break;
                default:
                    return false;
            }
            return false;
        }
    };

    public void getRepairs(){
        Network network = new Network();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Repairs...");
        progressDialog.show();
        network.registerObserver(this);
        network.getRepairs();
    }

    @Override
    public void update(String response) {
        repairs = new ArrayList<>();
        JSONArray jsonArray;
        if (!response.equals("{'error':'Not such repairs for this house.'}")){
            try {
                jsonArray = new JSONArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Repair repair = new Repair(
                            jsonObject.getString("Description"),
                            jsonObject.getString("Name"),
                            jsonObject.getString("Date"),
                            jsonObject.getString("Status"),
                            jsonObject.getString("PhotoRef"));
                    repairs.add(repair);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else{

        }
        router = new LandlordRouter(getSupportFragmentManager());
        progressDialog.dismiss();
        router.onNavigateToLandlordRepairs(repairs);
    }
}
