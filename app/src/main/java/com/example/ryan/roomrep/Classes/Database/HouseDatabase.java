package com.example.ryan.roomrep.Classes.Database;

import android.content.Context;

import com.example.ryan.roomrep.Classes.Database.TypeConverters.AmenityOptionTypeConverter;
import com.example.ryan.roomrep.Classes.Database.TypeConverters.AmenityTypeConverter;
import com.example.ryan.roomrep.Classes.Database.TypeConverters.UtilityTypeConverter;
import com.example.ryan.roomrep.Classes.House.Amenities.Amenity;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.Rent;
import com.example.ryan.roomrep.Classes.House.Utility.Utility;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {House.class, Rent.class}, version = 14, exportSchema = false)
@TypeConverters({UtilityTypeConverter.class})
public abstract class HouseDatabase extends RoomDatabase {

    private static HouseDatabase INSTANCE;

    public abstract HouseDao houseDao();

    public static synchronized HouseDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    HouseDatabase.class, "house_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }




}
