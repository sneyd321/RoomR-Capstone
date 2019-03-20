package com.example.ryan.roomrep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ChooseAccountActivity extends AppCompatActivity {

    ImageButton ibtnTenant;
    ImageButton ibtnLandlord;
    Button btnTenant;
    Button btnLandlord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
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
            startActivity(intent);
        }
    };

}
