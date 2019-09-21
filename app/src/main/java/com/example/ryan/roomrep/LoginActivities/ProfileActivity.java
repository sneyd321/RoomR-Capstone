package com.example.ryan.roomrep.LoginActivities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ryan.roomrep.Adapters.TenantListingsRecyclerViewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Profile.Profile;

import com.example.ryan.roomrep.Classes.Router.ProfileRouter;
import com.example.ryan.roomrep.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends AppCompatActivity implements FragmentEventListener  {


    public final static String SHARED_PREFERENCES = "PROFILE_PREFERENCES";

    List<House> houses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        houses = new ArrayList<>();

        Network network = Network.getInstance();
        network.registerObserver(this);
        network.getTenantListings();




    }
    public void addToSharedPreferences(Profile profile) {
        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putString("FirstName", profile.getFirstName());
        editor.putString("LastName", profile.getLastName());
        editor.putString("Email", profile.getEmail());
        editor.putString("Bio", profile.getBio());
        editor.apply();
    }


    @Override
    public void update(String response) {
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        Gson gson = new Gson();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                houses.add(gson.fromJson(jsonArray.get(i).toString(), House.class));
                houses.get(i).setUrl(jsonArray.getJSONObject(i).getString("image"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ProfileRouter profileRouter = new ProfileRouter(getSupportFragmentManager(), houses);
        profileRouter.onNavigateToAddProfile();
    }
}
