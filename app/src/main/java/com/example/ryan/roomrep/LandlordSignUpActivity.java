package com.example.ryan.roomrep;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LandlordSignUpActivity extends AppCompatActivity {


    EditText firstName;
    EditText lastName;
    EditText password;
    EditText password2;
    EditText email;
    Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_landlord_sign_up);

        firstName = findViewById(R.id.edtLSUfirstName);
        lastName = findViewById(R.id.edtLSUlastName);
        password = findViewById(R.id.edtLSUpassword);
        password2 = findViewById(R.id.edtLSUpassword2);
        email = findViewById(R.id.edtLSUemail);
        signup = findViewById(R.id.btnLSUsignup);
        signup.setOnClickListener(onSignUp);



    }

    View.OnClickListener onSignUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (password.getText().toString().equals(password2.getText().toString())){

                //String result = landlord.addValues();
                ConnectMySql connectMySql = new ConnectMySql();
                connectMySql.execute("");
                //Toast.makeText(LandlordSignUpActivity.this, result, Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(LandlordSignUpActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
            }

        }
    };
    private class ConnectMySql extends AsyncTask<String, Void, String> {
        String res = "";
        private String url = "jdbc:mysql://146.148.82.166:3306/RoomRDB";
        private String user = "sneyd321";
        private String pass = "";
        Landlord landlord = new Landlord(firstName.getText().toString(), lastName.getText().toString(), password2.getText().toString(), email.getText().toString());
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }



        @Override
        protected String doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);

                String result = "Database Connection Successful\n";
                Statement st = con.createStatement();

                String sql = "INSERT INTO Landlords VALUES ("
                        + "2,'"
                        + landlord.getFirstName() + "','"
                        + landlord.getLastName() + "',"
                        + "SHA1('" + landlord.getPassword() + "'),'"
                        + landlord.getEmail() + "')";

                st.executeUpdate(sql);
                res = result;
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(LandlordSignUpActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }



}
