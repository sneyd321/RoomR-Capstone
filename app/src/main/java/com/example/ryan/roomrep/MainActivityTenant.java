package com.example.ryan.roomrep;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.TenantRouter;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivityTenant extends AppCompatActivity implements FragmentEventListener {


    BottomNavigationView bottomMenu;
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
        bottomMenu = findViewById(R.id.navBar);
        bottomMenu.setOnNavigationItemSelectedListener(onBottomMenu);


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

            Network network = Network.getInstance();
            network.registerObserver(this);
            network.getTenantHouse(tenant);
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
                router.onNavigateToPayRent(tenant);
                break;
            case R.id.lblRepair:
                router.onNavigateToTenantRepairsList();
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
                    router.onNavigateToMessagesPeopleList(tenant,house);
                    break;
                case R.id.navRating:
                    //router.onNavigateToRatingLandlord(house.getLandlordEmail());
                    router.onNavigateToSlottingLandlord();
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
        JSONObject jsonObject = convertStringToJSONObject(response);
        Gson gson = new Gson();
        house = gson.fromJson(jsonObject.toString(), House.class);

        router.onNavigateToSearch(house);
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


    /*
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
        router = new TenantRouter(getSupportFragmentManager(), repairs);
        progressDialog.dismiss();
        router.onNavigateToTenantRepairsList();
    }
    */

    /*
    public void getRepairs(){
        Network network = new Network();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Repairs...");
        progressDialog.show();
        network.registerObserver(this);
        network.getRepairs();
    }
    */
    /*
    public void setViewRepairsList(){
        getRepairs();
    }
    */
    //public String GetChatRoomNameInMainActivityTenant(){return chatRoomNameInMainActivityTenant;}




}








