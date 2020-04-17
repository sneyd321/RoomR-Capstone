package com.example.ryan.roomrep.Classes.Login;

import android.os.Parcel;
import android.os.Parcelable;

public class Login implements Parcelable {
    private String email;
    private String password;
    private String userType;

    public Login(String email, String password, String userType){
        this.email = email;
        this.password = password;
        this.userType = userType;
    }


    protected Login(Parcel in) {
        email = in.readString();
        password = in.readString();
        userType = in.readString();
    }

    public static final Creator<Login> CREATOR = new Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel in) {
            return new Login(in);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };

    public String getUserType() {
        return this.userType;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(userType);
    }
}
