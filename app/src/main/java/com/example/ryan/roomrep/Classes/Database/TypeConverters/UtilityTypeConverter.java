package com.example.ryan.roomrep.Classes.Database.TypeConverters;

import com.example.ryan.roomrep.Classes.House.Utility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.room.TypeConverter;

public class UtilityTypeConverter {

    private Gson gson;

    public UtilityTypeConverter() {
        gson = new Gson();
    }
    @TypeConverter
    public List<Utility> stringToList(String value) {
        if (value == null) {
            return null;
        }
        try {
            List<Utility> list = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(value);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Utility utility = gson.fromJson(jsonObject.toString(), Utility.class);
                list.add(utility);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public String listToString(List<Utility> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
