package com.example.ryan.roomrep.Classes.Database;

import android.content.Context;

import com.example.ryan.roomrep.Classes.Database.TypeConverters.ServiceTypeConverter;
import com.example.ryan.roomrep.Classes.Database.TypeConverters.UtilityTypeConverter;
import com.example.ryan.roomrep.Classes.Users.Homeowner;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Users.Tenant;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {House.class, Homeowner.class, Tenant.class}, version = 15, exportSchema = false)
@TypeConverters({ServiceTypeConverter.class, UtilityTypeConverter.class})
public abstract class RoomRDatabase extends RoomDatabase {

    private static RoomRDatabase instance;


    public abstract HouseDao houseDao();

    public abstract HomeownerDao homeownerDao();

    public abstract TenantDao tenantDao();

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