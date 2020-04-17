package com.example.ryan.roomrep.Classes.House;

import com.example.ryan.roomrep.Classes.Users.Tenant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "house_table", primaryKeys = {"address"})
public class House {

    @NotNull
    private String address;

    @Embedded
    private OntarioLease lease;

    @Ignore
    private List<Tenant> tenants;


    public House(String address, OntarioLease lease) {
        this.address = address;
        this.lease = lease;
        tenants = new ArrayList<>();
    }

    public House(String address, OntarioLease lease, List<Tenant> tenants) {
        this.address = address;
        this.lease = lease;
        this.tenants = tenants;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OntarioLease getLease() {
        return lease;
    }

    public void setLease(OntarioLease lease) {
        this.lease = lease;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }
}
