package com.example.ryan.roomrep.Classes.Database;

import com.example.ryan.roomrep.Classes.Users.Homeowner;
import com.example.ryan.roomrep.Classes.Users.Tenant;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TenantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Tenant tenant);

    @Update
    void update(Tenant Tenant);

    @Delete
    void delete(Tenant tenant);

    @Query("SELECT * FROM tenant_table")
    LiveData<List<Tenant>> getTenants();


}
