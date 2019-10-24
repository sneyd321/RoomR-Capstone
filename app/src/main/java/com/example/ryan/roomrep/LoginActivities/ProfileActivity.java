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
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends AppCompatActivity  {


    public final static String SHARED_PREFERENCES = "PROFILE_PREFERENCES";

    List<House> houses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        houses = new ArrayList<>();

        ProfileRouter profileRouter = new ProfileRouter(getSupportFragmentManager(), houses);
        profileRouter.onNavigateToAddProfile();

    }
}
