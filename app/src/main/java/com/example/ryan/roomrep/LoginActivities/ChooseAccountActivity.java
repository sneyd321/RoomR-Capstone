package com.example.ryan.roomrep.LoginActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;

public class ChooseAccountActivity extends AppCompatActivity {

    ImageButton ibtnTenant;
    ImageButton ibtnLandlord;
    Button btnTenant;
    Button btnLandlord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);

        btnTenant = findViewById(R.id.btnTenant);
        ibtnTenant = findViewById(R.id.ibtnTenant);
        btnLandlord = findViewById(R.id.btnLandlord);
        ibtnLandlord = findViewById(R.id.ibtnLandlord);


        btnTenant.setOnClickListener(onTenant);
        ibtnTenant.setOnClickListener(onTenant);

        btnLandlord.setOnClickListener(onLandlord);
        ibtnLandlord.setOnClickListener(onLandlord);

    }


    View.OnClickListener onTenant = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(ChooseAccountActivity.this, "Tenant", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener onLandlord = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ChooseAccountActivity.this, LandlordSignUpActivity.class);
            //Intent intent = new Intent(ChooseAccountActivity.this, MainActivityTenant.class);
            startActivity(intent);
        }
    };

}
