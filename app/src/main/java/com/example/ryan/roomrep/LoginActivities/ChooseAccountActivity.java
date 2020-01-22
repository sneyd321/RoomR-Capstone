package com.example.ryan.roomrep.LoginActivities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ryan.roomrep.R;

public class ChooseAccountActivity extends AppCompatActivity {


    Button btnTenant;
    Button btnLandlord;
    TextView txtGoBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);

        btnTenant = findViewById(R.id.btnTenant);
        btnLandlord = findViewById(R.id.btnLandlord);
        txtGoBack = findViewById(R.id.txtCAback);
        txtGoBack.setOnTouchListener(onGoBack);


        btnTenant.setOnClickListener(onTenant);

        btnLandlord.setOnClickListener(onLandlord);


    }


    View.OnClickListener onTenant = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ChooseAccountActivity.this, TenantSignUpActivity.class);
            startActivity(intent);
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

    View.OnTouchListener onGoBack = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Intent intent = new Intent(ChooseAccountActivity.this, LoginActivity.class);
            startActivity(intent);
            return false;
        }
    };

}
