package com.example.ryan.roomrep.Classes.Database.TypeConverters;

import com.example.ryan.roomrep.Classes.House.Amenities.AmenityOption;
import com.example.ryan.roomrep.Classes.Iterator.JSONArrayIterator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class TypeConverter<T> {

    public List<T> stringToList(String value) {
        if (value == null) {
            return null;
        }
        try {
            List<T> list = new ArrayList<>();
            Gson gson = new Gson();
            JSONArray jsonArray = new JSONArray(value);
            JSONArrayIterator iterator = new JSONArrayIterator(jsonArray);
            Type listType = new TypeToken<List<T>>() {}.getType();
            while (iterator.hasNext()) {
                String jsonObject = iterator.next().toString();
                gson.fromJson(jsonObject, listType);

            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String listToString(List<T> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}
