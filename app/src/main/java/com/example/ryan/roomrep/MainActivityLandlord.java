package com.example.ryan.roomrep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

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

public class MainActivityLandlord extends AppCompatActivity {


    BottomNavigationView bottomMenu;

    Toolbar myToolbar;
    private StatePagerAdapter statePagerAdapter;
    private ViewPager viewPager;
    private int currentPosition = 0;

    private ArrayList<House> houses;
    private ArrayList<Repair> repairs;
    private Landlord landlord;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landlord);
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Intent intent = getIntent();
        landlord = new Landlord(intent.getStringExtra("LandlordFirstName"), intent.getStringExtra("LandlordLastName"), intent.getStringExtra("LandlordEmail"));
        getSupportActionBar().setTitle(landlord.getFirstName() + " " + landlord.getLastName());

        bottomMenu = findViewById(R.id.navBarLandlord);
        viewPager = findViewById(R.id.containerLandlord);


        statePagerAdapter = new StatePagerAdapter(getSupportFragmentManager());


        bottomMenu.setOnNavigationItemSelectedListener(onBottomMenu);
        setupPageAdapter(viewPager);

        houses = new ArrayList<>();
        repairs = new ArrayList<>();

    }

    public Landlord getLandlord(){
        return this.landlord;
    }

    public void setCurrentPosition(int currentPosition){
        this.currentPosition = currentPosition;
    }

    public int getCurrentPosition(){
        return this.currentPosition;
    }


    public Task<QuerySnapshot> getProfiles(){
        return db.collection("Profile").get();
    }


    public Task<QuerySnapshot> getTenants(String address){
        return db.collection("Landlord").document(landlord.getEmail()).collection("Houses").document(address).collection("Tenants").get();
    }





    public Task<QuerySnapshot> getHouses(){
        return db.collection("Landlord").document(landlord.getEmail()).collection("Houses").get();
    }

    public Task<QuerySnapshot> getRepairs(){
        return db.collection("Repair").get();
    }


    private void setupPageAdapter(ViewPager pager){
        StatePagerAdapter adapter = new StatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HouseFragment(), "House");
        adapter.addFragment(new AddHouseFragment(), "Add House");
        adapter.addFragment(new HouseDetailFragment(), "House Detail");
        adapter.addFragment(new AddTenantFragment(), "Add Tenant");
        adapter.addFragment(new RepairHistoryLandlordFragment(),"Repair History");
        adapter.addFragment(new ListTargetChatUserFragment(), "List chat user");
        adapter.addFragment(new MessagRFragment(), "MessagR");
        adapter.addFragment(new MessageLandlord(), "Group chat");
        adapter.addFragment(new AddGroup(), "Add group");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        viewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.side_menu_landlord, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.lblFindTenants:
                setViewPager(2);
                break;
            case R.id.lblSettingsLandlord:
                setViewPager(5);
                break;
            case R.id.lblLogoutLandlord:
                setViewPager(0);
                break;
            default:
                return false;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onBottomMenu = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navMessagR:
                    setViewPager(8);
                    break;
                case R.id.navSplitR:
                    break;
                case R.id.navUseR:

                    break;
                default:
                    return false;
            }
            return false;
        }
    };

    public ArrayList<House> getHouse(){
        return houses;
    }

    public ArrayList<Repair> getRepair(){
        return repairs;
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
