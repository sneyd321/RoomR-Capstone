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

import com.example.ryan.roomrep.Adapters.RepairRecyclerViewAdapter;
import com.example.ryan.roomrep.Adapters.StatePagerAdapter;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.TenantRouter;
import com.example.ryan.roomrep.Classes.Tenant;
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
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivityTenant extends AppCompatActivity implements FragmentEventListener {


    BottomNavigationView bottomMenu;
    TenantRouter router;


    private List<Repair> repairs;
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
        router = new TenantRouter(getSupportFragmentManager(), repairs);



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
                setViewRepairsList();
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
            Gson gson = new Gson();
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
        //Dismiss Dialogue Loading bar!
        router.onNavigateToTenantRepairsList(repairs);
    }


    public void getRepairs(){
        Network network = new Network();
        //Show network
        network.registerObserver(this);
        network.getRepairs();
    }

    public void setViewRepairsList(){
        getRepairs();
    }

    //public String GetChatRoomNameInMainActivityTenant(){return chatRoomNameInMainActivityTenant;}




}








