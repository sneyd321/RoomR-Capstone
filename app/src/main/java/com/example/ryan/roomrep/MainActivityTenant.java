package com.example.ryan.roomrep;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ryan.roomrep.Adapters.StatePagerAdapter;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.TenantFragments.PayRentFragment;
import com.example.ryan.roomrep.TenantFragments.CompleteRentFragment;
import com.example.ryan.roomrep.TenantFragments.ConfirmRentFragment;
import com.example.ryan.roomrep.TenantFragments.ExpertSystemFragment;
import com.example.ryan.roomrep.TenantFragments.ListingsFragment;
import com.example.ryan.roomrep.TenantFragments.MessagRFragment;
import com.example.ryan.roomrep.TenantFragments.RepairPictureFragment;
import com.example.ryan.roomrep.TenantFragments.SearchFragment;
import com.example.ryan.roomrep.TenantFragments.SendRepair;
import com.example.ryan.roomrep.TenantFragments.TenantRepairFragment;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivityTenant extends AppCompatActivity{


    String result = "";
    BottomNavigationView bottomMenu;


    private StatePagerAdapter statePagerAdapter;
    private ViewPager viewPager;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<Repair> repairs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tenant);

        bottomMenu = findViewById(R.id.navBar);
        viewPager = findViewById(R.id.container);


        statePagerAdapter = new StatePagerAdapter(getSupportFragmentManager());


        bottomMenu.setOnNavigationItemSelectedListener(onBottomMenu);
        repairs = new ArrayList<>();

        setupPageAdapter(viewPager);

    }


    private void setupPageAdapter(ViewPager pager){
        StatePagerAdapter adapter = new StatePagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new SearchFragment(), "Search");
        adapter.addFragment(new ListingsFragment(), "Listings");
        adapter.addFragment(new PayRentFragment(), "Pay Rent");
        adapter.addFragment(new ConfirmRentFragment(), "Confirm Rent");
        adapter.addFragment(new CompleteRentFragment(), "Complete Rent");
        adapter.addFragment(new RepairPictureFragment(), "Repair Photo");
        adapter.addFragment(new ExpertSystemFragment(), "Expert System");
        adapter.addFragment(new TenantRepairFragment(), "Tenant Repair");
        adapter.addFragment(new MessagRFragment(), "MessagR");
        adapter.addFragment(new SendRepair(), "Send Repair"); //9
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        viewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.side_menu_tenant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.lblRent:
                setViewPager(2);
                break;
            case R.id.lblRepair:
                setViewPager(5);
                break;
            case R.id.lblListing:
                setViewPager(0);
                break;
            case R.id.lblSettings:
                break;
            case R.id.lblLogout:
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

    //public Task<QuerySnapshot> getRepairs(){ return db.collection("Repair").get();}

    public ArrayList<Repair> getRepair(){
        return repairs;
    }




}








