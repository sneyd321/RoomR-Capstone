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
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.TenantRouter;
import com.example.ryan.roomrep.TenantFragments.ListTargetChatUserFragment;
import com.example.ryan.roomrep.TenantFragments.PayRentFragment;
import com.example.ryan.roomrep.TenantFragments.CompleteRentFragment;
import com.example.ryan.roomrep.TenantFragments.ConfirmRentFragment;
import com.example.ryan.roomrep.TenantFragments.ExpertSystemFragment;
import com.example.ryan.roomrep.TenantFragments.ListingsFragment;
import com.example.ryan.roomrep.TenantFragments.MessagRFragment;
import com.example.ryan.roomrep.TenantFragments.RepairPictureFragment;
import com.example.ryan.roomrep.TenantFragments.SearchFragment;
import com.example.ryan.roomrep.TenantFragments.SendRepairFragment;
import com.example.ryan.roomrep.TenantFragments.TenantRepairFragment;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivityTenant extends AppCompatActivity{


    BottomNavigationView bottomMenu;
    TenantRouter router;


    private ArrayList<Repair> repairs;
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
        bottomMenu = findViewById(R.id.navBar);
        bottomMenu.setOnNavigationItemSelectedListener(onBottomMenu);
        repairs = new ArrayList<>();

        router = new TenantRouter(getSupportFragmentManager());



        if (savedInstanceState == null) {
            router.onNavigateToListings();
        }

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
                router.onNavigateToPayRent();
                break;
            case R.id.lblRepair:
                router.onNavigateToRepairPicture();
                break;
            case R.id.lblListing:
                router.onNavigateToListings();
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
                    //router.onNavigateToMessages();
                    router.onNavigateToMessagesPeopleList();
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

    //public String GetChatRoomNameInMainActivityTenant(){return chatRoomNameInMainActivityTenant;}




}








