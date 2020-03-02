package com.example.ryan.roomrep.Classes.Location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;

import com.example.ryan.roomrep.Classes.Tenant.Validator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Entity;
import androidx.room.Ignore;


@Entity(tableName = "location_table")
public class Location {


    private String address;
    private String city;
    private String province;
    private String postalCode;


    @Ignore
    private Geocoder geocoder;
    @Ignore
    private Validator<Location> validator;

    public Location(String address, String city, String province, String postalCode) {
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    @Ignore
    public Location(Context context){
        this.geocoder = new Geocoder(context);
        this.validator = new LocationValidator();
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
            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);
                this.address = street;
                if (address.getAdminArea() != null && !address.getAdminArea().isEmpty()){
                    this.province = address.getAdminArea();
                }
                if (address.getLocality() != null && !address.getLocality().isEmpty()) {
                    this.city = address.getLocality();
                }
                if (address.getPostalCode() != null && !address.getPostalCode().isEmpty()){
                    this.postalCode = address.getPostalCode();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;

    }

    private void validateLocation() {
        for (Map.Entry<Integer, String> entry : this.getValidator().entrySet()){

        }
    }

    public Map<Integer, String> getValidator() {
        return this.validator.validate(this);
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
