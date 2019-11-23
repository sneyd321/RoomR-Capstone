package com.example.ryan.roomrep.Classes.Tenant;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Tenant implements Parcelable {


    private String firstName;
    private String lastName;
    private String password;
    private String password2;
    private String tenantEmail;
    private String landlordEmail;
    private String houseAddress;
    private String tenantRating;




    public Tenant(String firstName, String lastName, String email, String password, String password2, String landlordEmail, String houseAddress ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tenantEmail = email;
        this.password = password;
        this.password2 = password2;
        this.landlordEmail = landlordEmail;
        this.houseAddress = houseAddress;

    }

    public Tenant(String firstName, String lastName, String email, String password, String password2, String landlordEmail, String houseAddress, String tenantRating ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tenantEmail = email;
        this.password = password;
        this.password2 = password2;
        this.landlordEmail = landlordEmail;
        this.houseAddress = houseAddress;
        this.tenantRating = tenantRating;
    }

    public Tenant(String firstName, String lastName, String email, String password, String password2, String landlordEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tenantEmail = email;
        this.password = password;
        this.password2 = password2;
        this.landlordEmail = landlordEmail;
    }


    protected Tenant(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        password = in.readString();
        password2 = in.readString();
        tenantEmail = in.readString();
        landlordEmail = in.readString();
        houseAddress = in.readString();
        tenantRating = in.readString();
    }

    public static final Creator<Tenant> CREATOR = new Creator<Tenant>() {
        @Override
        public Tenant createFromParcel(Parcel in) {
            return new Tenant(in);
        }

        @Override
        public Tenant[] newArray(int size) {
            return new Tenant[size];
        }
    };

    public Map<Integer, String> getValidator() {
        Validator validator = new TenantValidator();
        return validator.validator(this);
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getPassword() {
        return password;
    }


    public String getTenantEmail() {
        return tenantEmail;
    }


    public String getLandlordEmail() {
        return landlordEmail;
    }


    public String getPassword2() {
        return password2;
    }

    public String getHouseAddress(){return houseAddress;}

    public String getTenantRating(){return  tenantRating;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(password);
        dest.writeString(password2);
        dest.writeString(tenantEmail);
        dest.writeString(landlordEmail);
        dest.writeString(houseAddress);
        dest.writeString(tenantRating);
    }


}
