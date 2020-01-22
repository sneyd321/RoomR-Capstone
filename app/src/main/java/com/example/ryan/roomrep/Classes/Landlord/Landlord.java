package com.example.ryan.roomrep.Classes.Landlord;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ryan.roomrep.Classes.Tenant.Validator;

import java.util.Map;

public class Landlord implements Parcelable {




    private String firstName;
    private String lastName;
    private String password;
    private String password2;
    private String email;
    private String repairrating;

    public Landlord(String firstName, String lastName, String password, String password2, String email, String repairrating){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.password2 = password2;
        this.email = email;
        this.repairrating = repairrating;
    }


    protected Landlord(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        password = in.readString();
        password2 = in.readString();
        email = in.readString();
        repairrating = in.readString();
    }

    public static final Creator<Landlord> CREATOR = new Creator<Landlord>() {
        @Override
        public Landlord createFromParcel(Parcel in) {
            return new Landlord(in);
        }

        @Override
        public Landlord[] newArray(int size) {
            return new Landlord[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRepairrating() {
        return repairrating;
    }

    public Map<Integer, String> getLandlordValidator() {
        Validator validator = new LandlordValidator();
        return validator.validate(this);
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }


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
        dest.writeString(email);
        dest.writeString(repairrating);
    }

}
