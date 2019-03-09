package com.example.ryan.roomrep;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivityTenant extends AppCompatActivity{



    BottomNavigationView bottomMenu;

    SearchFragment searchFragment;

    private StatePagerAdapter statePagerAdapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tenant);

        bottomMenu = findViewById(R.id.navBar);
        viewPager = findViewById(R.id.container);


        statePagerAdapter = new StatePagerAdapter(getSupportFragmentManager());


        bottomMenu.setOnNavigationItemSelectedListener(onBottomMenu);
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
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        viewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.side_menu, menu);
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
                //getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, searchFragment).commit();
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
}
