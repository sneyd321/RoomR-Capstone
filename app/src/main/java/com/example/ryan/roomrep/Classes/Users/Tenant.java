package com.example.ryan.roomrep.Classes.Users;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import androidx.room.Entity;
import androidx.room.Ignore;


@Entity(tableName = "tenant_table", primaryKeys = {"tenantEmail"})
public class Tenant {


    private String firstName;
    private String lastName;
    @Ignore
    private String password;
    @NotNull
    private String tenantEmail;
    private String houseAddress;
    private boolean isApproved;


    public Tenant(String firstName, String lastName, String tenantEmail, String password, String houseAddress, boolean isApproved) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tenantEmail = tenantEmail;
        this.password = password;
        this.houseAddress = houseAddress;
        this.isApproved = isApproved;
    }

    public Tenant(String firstName, String lastName, String tenantEmail, String houseAddress, boolean isApproved) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tenantEmail = tenantEmail;
        this.password = "";
        this.houseAddress = houseAddress;
        this.isApproved = isApproved;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTenantEmail() {
        return tenantEmail;
    }

    public String getHouseAddress(){return houseAddress;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTenantEmail(String tenantEmail) {
        this.tenantEmail = tenantEmail;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }


    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
