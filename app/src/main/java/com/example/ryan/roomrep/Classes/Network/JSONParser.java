package com.example.ryan.roomrep.Classes.Network;

import com.example.ryan.roomrep.Classes.House.AdditionalTerms;
import com.example.ryan.roomrep.Classes.House.HomeownerLocation;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.OntarioLease;
import com.example.ryan.roomrep.Classes.House.OntarioLeaseBuilder;
import com.example.ryan.roomrep.Classes.House.RentDetail;
import com.example.ryan.roomrep.Classes.House.RentalUnitLocation;
import com.example.ryan.roomrep.Classes.House.Service;
import com.example.ryan.roomrep.Classes.House.Utility;
import com.example.ryan.roomrep.Classes.Login.Login;
import com.example.ryan.roomrep.Classes.Users.Homeowner;
import com.example.ryan.roomrep.Classes.Users.Tenant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

import static com.example.ryan.roomrep.Classes.Network.Network.JSON;

public class JSONParser {

    private static JSONParser instance = null;

    private Gson gson;

    public static JSONParser getInstance() {
        if (instance == null) {
            return new JSONParser();
        }
        return instance;
    }

    private JSONParser() {
        gson = new Gson();
    }

    public Gson getGson(){
        return this.gson;
    }



    private JSONArray stringToJSONArray(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            return jsonArray;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JSONObject getJSONObjectFromArrayAtIndex(JSONArray jsonArray, int index) {
        try {
            return jsonArray.getJSONObject(index);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JSONArray getJSONArrayFromArrayAtIndex(JSONArray jsonArray, int index) {
        try {
            return jsonArray.getJSONArray(index);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JSONObject stringToJSON(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Homeowner getHomeowner(String response) {
        JSONArray jsonArray = stringToJSONArray(response);
        JSONObject jsonObject = getJSONObjectFromArrayAtIndex(jsonArray, 0);
        return gson.fromJson(jsonObject.toString(), Homeowner.class);
    }

    public List<House> getHouses(String response) {
        JSONArray jsonArray = stringToJSONArray(response);
        JSONArray housesArray = getJSONArrayFromArrayAtIndex(jsonArray, 1);
        return parseHouseJSONObject(housesArray);
    }



    private List<House> parseHouseJSONObject(JSONArray housesArray) {
        List<House> houses = new ArrayList<>();
        for (int i = 0; i < housesArray.length(); i++) {
            try {
                JSONObject jsonObject = housesArray.getJSONObject(i);
                RentalUnitLocation rentalUnitLocation = gson.fromJson(getJSONObject(jsonObject, "rentalUnitLocation"), RentalUnitLocation.class);
                HomeownerLocation homeownerLocation = gson.fromJson(getJSONObject(jsonObject, "homeownerLocation"), HomeownerLocation.class);
                RentDetail rentDetail = gson.fromJson(getJSONObject(jsonObject, "rentDetail"), RentDetail.class);
                List<Service> services = gson.fromJson(getJSONArray(jsonObject, "services"), new TypeToken<List<Service>>(){}.getType());
                List<Utility> utilities = gson.fromJson(getJSONArray(jsonObject, "utilities"), new TypeToken<List<Utility>>(){}.getType());
                AdditionalTerms additionalTerms = gson.fromJson(getJSONObject(jsonObject, "additionalTerms"), AdditionalTerms.class);
                OntarioLeaseBuilder ontarioLeaseBuilder = new OntarioLeaseBuilder();
                ontarioLeaseBuilder
                        .setRentalUnitLocation(rentalUnitLocation)
                        .setHomeownerLocation(homeownerLocation)
                        .setRentDetail(rentDetail)
                        .setAdditionalTerms(additionalTerms);
                for (Service service : services) {
                    ontarioLeaseBuilder.addService(service);
                }
                for (Utility utility : utilities) {
                    ontarioLeaseBuilder.addUtility(utility);
                }
                String address = getJSONString(jsonObject, "address");
                List<Tenant> tenants = gson.fromJson(getJSONArray(jsonObject, "tenants"), new TypeToken<List<Tenant>>(){}.getType());
                OntarioLease lease = ontarioLeaseBuilder.build();
                houses.add(new House(address, lease, tenants));
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return houses;
    }

    private String getJSONObject(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getJSONObject(key).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    private String getJSONArray(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getJSONArray(key).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    private String getJSONString(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }







    public List<Tenant> parseTenantsJSONArray(JSONArray jsonArray) throws JSONException {
        List<Tenant> tenants = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Tenant tenant = gson.fromJson(jsonObject.toString(), Tenant.class);
            tenants.add(tenant);
        }
        return tenants;
    }

    public String parseLoginToken(String response) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(response);
            return jsonObject.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public RequestBody getLoginRequestBody(Login login) {
        String json = gson.toJson(login);
        return RequestBody.create(JSON, json);
    }

}
