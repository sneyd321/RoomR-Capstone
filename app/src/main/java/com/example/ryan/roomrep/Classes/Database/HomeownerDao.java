package com.example.ryan.roomrep.Classes.Database;

import com.example.ryan.roomrep.Classes.Users.Homeowner;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface HomeownerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Homeowner homeowner);

    @Update
    void update(Homeowner homeowner);

    @Delete
    void delete(Homeowner homeowner);

    @Query("SELECT * FROM homeowner_table WHERE email = :email LIMIT 1")
    LiveData<Homeowner> getHomeownerByEmail(String email);


}
