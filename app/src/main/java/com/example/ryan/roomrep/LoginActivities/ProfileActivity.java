package com.example.ryan.roomrep.LoginActivities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ryan.roomrep.Classes.Profile.Profile;

import com.example.ryan.roomrep.Classes.Router.ProfileRouter;
import com.example.ryan.roomrep.R;


public class ProfileActivity extends AppCompatActivity {


    public final static String SHARED_PREFERENCES = "PROFILE_PREFERENCES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




        ProfileRouter profileRouter = new ProfileRouter(getSupportFragmentManager());
        profileRouter.onNavigateToAddProfile();



    }
    public void addToSharedPreferences(Profile profile) {
        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putString("FirstName", profile.getFirstName());
        editor.putString("LastName", profile.getLastName());
        editor.putString("Email", profile.getEmail());
        editor.putString("Bio", profile.getBio());
        editor.apply();
    }
    




}
