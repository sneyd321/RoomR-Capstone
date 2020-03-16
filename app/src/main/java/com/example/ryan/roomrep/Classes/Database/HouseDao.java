package com.example.ryan.roomrep.Classes.Database;

import com.example.ryan.roomrep.Classes.Lease.House;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

@Dao
public interface HouseDao {

    @Insert
    void insert(House house);

    @Update
    void update(House house);

    @Delete
    void delete(House house);

    @Transaction
    @Query("SELECT * FROM house_table")
    LiveData<List<House>> getAllHouses();



}