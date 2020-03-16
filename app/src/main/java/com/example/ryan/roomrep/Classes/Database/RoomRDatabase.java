package com.example.ryan.roomrep.Classes.Database;

import android.content.Context;

import com.example.ryan.roomrep.Classes.Lease.House;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {House.class}, version = 3, exportSchema = false)
public abstract class RoomRDatabase extends RoomDatabase {

    private static RoomRDatabase instance;

    public abstract HouseDao houseDao();

    public static synchronized RoomRDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RoomRDatabase.class, "roomr_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }




}