package com.example.ryan.roomrep.LoginActivities;

import android.content.Context;
import android.content.Intent;
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

    ProfileRouter profileRouter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileRouter = new ProfileRouter(getSupportFragmentManager());
        profileRouter.onNavigateToAddProfile();

    }


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        profileRouter.popBackStack();
    }
}
