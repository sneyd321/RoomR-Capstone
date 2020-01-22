package com.example.ryan.roomrep.LoginActivities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ryan.roomrep.Classes.Router.ProfileRouter;
import com.example.ryan.roomrep.R;


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
