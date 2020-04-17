package com.example.ryan.roomrep.Classes.Database.TypeConverters;

import com.example.ryan.roomrep.Classes.House.Service;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.room.TypeConverter;

public class ServiceTypeConverter {

    private Gson gson;

    public ServiceTypeConverter() {
        gson = new Gson();
    }

    @TypeConverter
    public List<Service> stringToList(String value) {
        if (value == null) {
            return null;
        }
        try {
            List<Service> list = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(value);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Service service = gson.fromJson(jsonObject.toString(), Service.class);
                list.add(service);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public String listToString(List<Service> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
