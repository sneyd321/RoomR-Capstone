package com.example.ryan.roomrep;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.example.ryan.roomrep.Adapters.StatePagerAdapter;
import com.example.ryan.roomrep.TenantFragments.CompleteRentFragment;
import com.example.ryan.roomrep.TenantFragments.ConfirmRentFragment;
import com.example.ryan.roomrep.TenantFragments.ExpertSystemFragment;
import com.example.ryan.roomrep.TenantFragments.ListingsFragment;
import com.example.ryan.roomrep.TenantFragments.MessagRFragment;
import com.example.ryan.roomrep.TenantFragments.PayRentFragment;
import com.example.ryan.roomrep.TenantFragments.RepairPictureFragment;
import com.example.ryan.roomrep.TenantFragments.SearchFragment;
import com.example.ryan.roomrep.TenantFragments.TenantRepairFragment;

public class MainActivityLandlord extends AppCompatActivity {


    BottomNavigationView bottomMenu;

    Toolbar myToolbar;
    private StatePagerAdapter statePagerAdapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landlord);
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        bottomMenu = findViewById(R.id.navBarLandlord);
        viewPager = findViewById(R.id.containerLandlord);


        statePagerAdapter = new StatePagerAdapter(getSupportFragmentManager());


        bottomMenu.setOnNavigationItemSelectedListener(onBottomMenu);
        setupPageAdapter(viewPager);


    }

    private void setupPageAdapter(ViewPager pager){
        StatePagerAdapter adapter = new StatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AddHouseFragment(), "Add House");
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


}
