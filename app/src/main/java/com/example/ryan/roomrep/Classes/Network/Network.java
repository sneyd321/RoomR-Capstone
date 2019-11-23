package com.example.ryan.roomrep.Classes.Network;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Login;
import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.Classes.Rent.Payment;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Search.Search;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Network implements NetworkObservable {

    private final String SERVER_URL2 = "http://10.16.25.62:8080/";
    //private final String SERVER_URL = "https://roomr-222721.appspot.com/";
    private final String SERVER_URL = "http://192.168.0.106:8080/";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");





    private static Network NETWORK_INSTANCE = null;

    public static Network getInstance() {
        if (NETWORK_INSTANCE == null) {
            NETWORK_INSTANCE = new Network();
        }
        return NETWORK_INSTANCE;
    }

    private FragmentEventListener fragmentEventListener;

    @Override
    public void registerObserver(FragmentEventListener fragmentEventListener) {
        this.fragmentEventListener = fragmentEventListener;
    }

    @Override
    public void clearObserver() {
       this.fragmentEventListener = null;
    }

    @Override
    public void notifyObserver(String response) {
        if (fragmentEventListener != null){
            this.fragmentEventListener.update(response);
            clearObserver();
        }

    }

    public void uploadHouse(final House house) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Gson gson = new Gson();
            String json = gson.toJson(house);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(SERVER_URL + "AddHouse/" + user.getUid())
                    .post(body)
                    .build();


            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        fragmentEventListener.update(response.body().string());
                    }
                    response.close();
                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }


    }


    public void getLandlordHouses(Landlord landlord){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Gson gson = new Gson();
            String json = gson.toJson(landlord);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(SERVER_URL + "GetHouse/" + user.getUid())
                    .post(body)
                    .build();

            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        fragmentEventListener.update(response.body().string());
                    }
                    response.close();
                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }

    public void signUpLandlord(final Landlord landlord){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Gson gson = new Gson();
            String json = gson.toJson(landlord);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(SERVER_URL + "SignUpTempLandlord/" + user.getUid())
                    .post(body)
                    .build();

            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){

                    }
                    response.close();
                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }

    public void getLandlord(Login login) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            final Gson gson = new Gson();
            String json = gson.toJson(login);
            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
                    .url(SERVER_URL + "GetLandlord/" + user.getUid())
                    .post(body)
                    .build();

            OkHttpClient client = new OkHttpClient();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        fragmentEventListener.update(response.body().string());
                    }
                    response.close();

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }

    public void getTenant(Login login) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            final Gson gson = new Gson();
            String json = gson.toJson(login);
            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
                    .url(SERVER_URL + "GetTenant/" + user.getUid())
                    .post(body)
                    .build();

            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        fragmentEventListener.update(response.body().string());
                    }
                    response.close();

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }

    public void addProfile(Profile profile) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            final Gson gson = new Gson();
            String json = gson.toJson(profile);
            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
                    .url(SERVER_URL + "AddProfile/" + user.getUid())
                    .post(body)
                    .build();

            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        fragmentEventListener.update(response.body().string());
                    }
                    response.close();

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }


    public void postListing(House house) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            final Gson gson = new Gson();
            String json = gson.toJson(house);
            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
                    .url(SERVER_URL + "PostListing/" + user.getUid())
                    .post(body)
                    .build();

            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        notifyObserver(response.body().string());
                    }
                    response.close();

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }



    public void uploadRepairImage(File photo, String language) {
        final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        JSONObject jsonObject = null;

        String formatJson = "{'Language': '" + language +"'}";
        try {
            jsonObject = new JSONObject(formatJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Look at request body for .addFormDataPart to send Json
        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("Photo", "TestNetworkRepair.png", RequestBody.create(MEDIA_TYPE_PNG, photo))
                .addFormDataPart("Language", jsonObject.toString())
                .build();

        Request request = new Request.Builder()
                .url(SERVER_URL2 + "AddPhoto")
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    notifyObserver(response.body().string());
                }
            }
        });
    }

    public void addRepair(Repair repair){
        final Gson gson = new Gson();
        String json = gson.toJson(repair);

        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(SERVER_URL2 + "AddRepair")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    //I want the response to navigate back to the Tenant Listings which sees all the repairs view!!!
                    fragmentEventListener.update(response.body().string());
                }
                response.close();

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });

    }

    public void getRepairs(String houseAddress){
        JSONObject json = new JSONObject();
        try {
            json.put("houseAddress", houseAddress);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        String jsonString = json.toString();
        RequestBody body = RequestBody.create(JSON, jsonString);

        Request request = new Request.Builder()
                .url(SERVER_URL2 + "GetRepairs")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    fragmentEventListener.update(response.body().string());
                }
                response.close();
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }
    public void contactLandlord(Profile profile) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            final Gson gson = new Gson();
            String json = gson.toJson(profile);
            RequestBody body = RequestBody.create(JSON, json);


            Request request = new Request.Builder()
                    .url(SERVER_URL + "ContactLandlord/" + user.getUid())
                    .post(body)
                    .build();

            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        notifyObserver(response.body().string());
                    }
                    response.close();

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }

    public void searchListing(Search search) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            String json = search.convertToJSON();
            RequestBody body = RequestBody.create(JSON, json);


            Request request = new Request.Builder()
                    .url(SERVER_URL + "SearchListings/" + user.getUid())
                    .post(body)
                    .build();

            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        notifyObserver(response.body().string());
                    }
                    response.close();

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }

    public void contactProfile(Profile profile) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            String json = profile.convertToJSON();
            RequestBody body = RequestBody.create(JSON, json);


            Request request = new Request.Builder()
                    .url(SERVER_URL + "ContactProfile/" + user.getUid())
                    .post(body)
                    .build();

            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        notifyObserver(response.body().string());
                    }
                    response.close();

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }

    public void updateRepair(Repair repair) {
        final Gson gson = new Gson();
        String json = gson.toJson(repair);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(SERVER_URL2 + "UpdateRepairs")
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    notifyObserver(response.body().string());
                }
                response.close();

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }

    public void updateRepairLandlord(Repair repair) {
        final Gson gson = new Gson();
        String json = gson.toJson(repair);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(SERVER_URL2 + "UpdateRepairsLandlord")
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    notifyObserver(response.body().string());
                }
                response.close();

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }

    public void getRepairmans(String address, String category){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("houseAddress",address);
            jsonObject.put("category",category);
        }catch (JSONException e){
            e.printStackTrace();
        }
        String json = jsonObject.toString();


        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(SERVER_URL2 + "GetRepairman")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    fragmentEventListener.update(response.body().string());
                }
                response.close();
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }

    public void convertProfileToTenant(Profile profile) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            final Gson gson = new Gson();
            String json = gson.toJson(profile);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(SERVER_URL + "ConvertProfileToTenant/" + user.getUid())
                    .post(body)
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        notifyObserver(response.body().string());
                    }
                    response.close();

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }


    private JSONObject addClientTokenToTenantRequest(Tenant tenant, String token) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("firstName", tenant.getFirstName());
        json.put("lastName", tenant.getLastName());
        json.put("tenantEmail", tenant.getTenantEmail());
        json.put("landlordEmail", tenant.getLandlordEmail());
        json.put("password", tenant.getPassword());
        json.put("password2", tenant.getPassword2());
        json.put("token", token);
        return json;

    }

    public void signupTenant(Tenant tenant, String token) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            JSONObject json;
            try {
                json = addClientTokenToTenantRequest(tenant, token);
            } catch (JSONException e) {
                return;
            }


            RequestBody body = RequestBody.create(JSON, json.toString());
            Request request = new Request.Builder()
                    .url(SERVER_URL + "SignUpTenant/" + user.getUid())
                    .post(body)
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        notifyObserver(response.body().string());
                    }
                    response.close();

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }

    public void makePayment(Payment payment) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            final Gson gson = new Gson();
            String json = gson.toJson(payment);
            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
                    .url(SERVER_URL + "MakePayment/" + user.getUid())
                    .post(body)
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        notifyObserver(response.body().string());
                    }
                    response.close();

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }

    public void getPayments(Landlord landlord) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            final Gson gson = new Gson();
            String json = gson.toJson(landlord);
            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
                    .url(SERVER_URL + "GetLandlordPayments/" + user.getUid())
                    .post(body)
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        notifyObserver(response.body().string());
                    }
                    response.close();

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }

    public void getTenantHouse(Tenant tenant) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            final Gson gson = new Gson();
            String json = gson.toJson(tenant);
            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
                    .url(SERVER_URL + "GetTenantHouse/" + user.getUid())
                    .post(body)
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        notifyObserver(response.body().string());
                    }
                    response.close();

                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });

        }

    }

    public void sendPaymentReminder(Tenant tenant) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            final Gson gson = new Gson();
            String json = gson.toJson(tenant);
            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
                    .url(SERVER_URL + "SendPaymentNotification/" + user.getUid())
                    .post(body)
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){

                    }


                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }

    }


    public void updateProfile(Profile profile) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            final Gson gson = new Gson();
            String json = gson.toJson(profile);
            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
                    .url(SERVER_URL + "UpdateProfile/" + user.getUid())
                    .post(body)
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){

                    }
                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }
    }

    public void getLandlordRating(String houseAddress) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("houseAddress",houseAddress);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(JSON, jsonObject.toString());

            Request request = new Request.Builder()
                    .url(SERVER_URL2 + "GetLandlordWithHouseAddress")
                    .post(body)
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){
                        notifyObserver(response.body().string());
                        response.close();

                    }
                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }
    }

    public void updateHouse(String province, String city, String houseAddress) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            JSONObject jsonObject = formatLocationObject(province, city, houseAddress);
            RequestBody body = RequestBody.create(JSON, jsonObject.toString());

            Request request = new Request.Builder()
                    .url(SERVER_URL + "AddLocationToHouse/" + user.getUid())
                    .post(body)
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){

                    }
                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }
    }

    private JSONObject formatLocationObject(String province, String city, String houseAddress) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("province", province);
            jsonObject.put("city", city);
            jsonObject.put("houseAddress", houseAddress);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public void removeHouse(House house) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Gson gson = new Gson();
            String json = gson.toJson(house);
            RequestBody body = RequestBody.create(JSON, json);

            Request request = new Request.Builder()
                    .url(SERVER_URL + "RemoveHouse/" + user.getUid())
                    .post(body)
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()){

                    }
                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
            });
        }
    }


}
