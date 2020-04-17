package com.example.ryan.roomrep.Activities;

import androidx.annotation.NonNull;

import com.example.ryan.roomrep.App.App;
import com.example.ryan.roomrep.Classes.Database.HomeownerRepository;
import com.example.ryan.roomrep.Classes.Database.HouseRepository;
import com.example.ryan.roomrep.Classes.Database.TenantRepository;
import com.example.ryan.roomrep.Classes.Login.Login;
import com.example.ryan.roomrep.Classes.Network.JSONParser;
import com.example.ryan.roomrep.Classes.Users.Homeowner;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Network.NetworkObserver;
import com.example.ryan.roomrep.App.Dialog.Dialog;
import com.example.ryan.roomrep.Classes.Users.Tenant;
import com.example.ryan.roomrep.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import okhttp3.Request;

import static com.example.ryan.roomrep.App.Permission.INTERNET_PERMISSION_REQUEST_CODE;

public class MainActivityLandlord extends AppCompatActivity implements NetworkObserver {
    BottomNavigationView bottomMenu;
    Toolbar myToolbar;


    AppBarConfiguration appBarConfiguration;
    NavController navController;


    Network network;
    HomeownerRepository homeownerRepository;
    HouseRepository houseRepository;
    TenantRepository tenantRepository;
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landlord);

        login = getIntent().getParcelableExtra("LOGIN");
        if (login == null){
            Dialog dialog = new Dialog(this);
            dialog.setMessage("Invalid login credentials.");
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    onDestroy();
                }
            });
            dialog.buildErrorDialog().show();

        }

        homeownerRepository = new HomeownerRepository(getApplication());
        houseRepository = new HouseRepository(getApplication());
        tenantRepository = new TenantRepository(getApplication());


        network = Network.getInstance();
        network.registerObserver(this);
        network.getUserData(login);


        bottomMenu = findViewById(R.id.navBarLandlord);
        myToolbar = findViewById(R.id.toolbarLandlord);
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));


        navController = Navigation.findNavController(this, R.id.nav_landlord_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(myToolbar, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomMenu, navController);

        navController.addOnDestinationChangedListener(onDestinationChangedListener);


        if (savedInstanceState == null) {
            bottomMenu.getMenu().getItem(1).setChecked(true);
        }
    }




    public Login getLogin() {
        return this.login;
    }




    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_landlord_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

        if(!Navigation.findNavController(this, R.id.nav_landlord_host_fragment).popBackStack()){
            finish();
        }
    }


    private NavController.OnDestinationChangedListener onDestinationChangedListener = new NavController.OnDestinationChangedListener() {
        @Override
        public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
            myToolbar.setTitle(destination.getLabel());

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_landlord_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == INTERNET_PERMISSION_REQUEST_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Internet permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Internet permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void update(String response) {
        JSONParser jsonParser = JSONParser.getInstance();
        Homeowner homeowner = jsonParser.getHomeowner(response);
        homeownerRepository.insert(homeowner);
        List<House> houses = jsonParser.getHouses(response);
        for (House house : houses) {
            houseRepository.insert(house);
            for (Tenant tenant : house.getTenants()) {
                tenantRepository.insert(tenant);
            }
        }
    }


}
