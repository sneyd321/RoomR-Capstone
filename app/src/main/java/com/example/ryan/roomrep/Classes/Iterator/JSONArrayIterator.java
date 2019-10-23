package com.example.ryan.roomrep.Classes.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JSONArrayIterator implements Iterator {


    private JSONArray jsonArray;
    private int currentPosition = 0;

    public JSONArrayIterator(JSONArray jsonArray){
        this.jsonArray = jsonArray;
    }


    @Override
    public JSONObject next() {
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(currentPosition);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        currentPosition += 1;
        return jsonObject;
    }


    @Override
    public boolean hasNext() {
        try {
            if (currentPosition >= jsonArray.length() && jsonArray.get(currentPosition) != null){
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
