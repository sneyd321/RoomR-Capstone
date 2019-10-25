package com.example.ryan.roomrep.TenantFragments;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ryan.roomrep.Classes.Router.LandlordRouter;
import com.example.ryan.roomrep.Classes.Router.RentRouter;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.R;

public class RentActivity extends AppCompatActivity {


    RentRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        handleIntent(getIntent());

        Bundle bundle = getIntent().getExtras();
        Tenant tenant = bundle.getParcelable("TENANT_INFO");

        if (router == null) {

            router = new RentRouter(getSupportFragmentManager());
            router.onNavigateToPayRent(tenant);
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
            router = new RentRouter(getSupportFragmentManager());
            router.onNavigateToCompleteRent();
        }
    }

}
