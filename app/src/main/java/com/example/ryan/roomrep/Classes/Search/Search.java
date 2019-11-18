package com.example.ryan.roomrep.Classes.Search;

import com.example.ryan.roomrep.Classes.Tenant.Validator;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;

public class Search {

    private String province;
    private String city;
    private int price;
    private Map<String, Boolean> amenities;


    public Search(String province, String city, int price, Map<String, Boolean> amenities) {
        this.province = province;
        this.city = city;
        this.price = price;
        this.amenities = amenities;
    }


    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public int getPrice() {
        return price;
    }

    public Map<String, Boolean> getAmenities() {
        return amenities;
    }

    public Map<Integer, String> getValidator() {
        Validator validator = new SearchValidator();
        return validator.validator(this);
    }

    public String convertToJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }





}
