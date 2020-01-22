package com.example.ryan.roomrep.Classes.Database.TypeConverters;

import com.example.ryan.roomrep.Classes.House.Utility.Utility;
import com.example.ryan.roomrep.Classes.Iterator.JSONArrayIterator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.jdbc.Util;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UtilityTypeConverter extends TypeConverter<Utility> {


    @androidx.room.TypeConverter
    public List<Utility> stringToList(String value) {
        if (value == null) {
            return null;
        }
        try {
            List<Utility> list = new ArrayList<>();
            Gson gson = new Gson();
            JSONArray jsonArray = new JSONArray(value);
            JSONArrayIterator iterator = new JSONArrayIterator(jsonArray);

            while (iterator.hasNext()) {
                String jsonObject = iterator.next().toString();
                list.add(gson.fromJson(jsonObject, Utility.class));

            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @androidx.room.TypeConverter
    public String listToString(List<Utility> list) {
        return super.listToString(list);
    }
}
