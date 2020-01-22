package com.example.ryan.roomrep.Classes.House;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "location_table")
public class Location {


    private String address;
    private String city;
    private String province;
    private String postalCode;


    @Ignore
    private Geocoder geocoder;

    public Location(String address, String city, String province, String postalCode) {
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    @Ignore
    public Location(Context context){
        this.geocoder = new Geocoder(context);
        this.address = "";
        this.city = "";
        this.province = "";
        this.postalCode = "";
    }



    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }


    public Location getLocationDetailsFromGeocoder(String street) {
        try {
            List<Address> addresses = geocoder.getFromLocationName(street, 1);
            if (addresses != null) {
                Address address = addresses.get(0);
                this.address = street;
                if (!address.getAdminArea().isEmpty()){
                    this.province = address.getAdminArea();
                }
                if (!address.getLocality().isEmpty()) {
                    this.city = address.getLocality();
                }
                if (!address.getPostalCode().isEmpty()){
                    this.postalCode = address.getPostalCode();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;

    }

    public String getFormattedSecondaryLocation() {
        return this.city + ", " + this.getProvince() + " " + this.postalCode;
    }


    public void getAddressFromLatitudeAndLongitude(double latitude, double longitude) {
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null){
                Address address = addresses.get(0);
                if (!address.getThoroughfare().isEmpty()){
                    this.address = address.getThoroughfare();
                }
                if (!address.getAdminArea().isEmpty()){
                    this.province = address.getAdminArea();
                }
                if (!address.getLocality().isEmpty()) {
                    this.city = address.getLocality();
                }
                if (!address.getPostalCode().isEmpty()){
                    this.postalCode = address.getPostalCode();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public boolean handleLocationPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    return false;
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
                    return true;
                }
            }
            else {
                return true;
            }
        }
        return true;
    }

}
