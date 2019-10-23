package com.example.ryan.roomrep.Classes.Network;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Login;
import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Search;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Network implements NetworkObservable {

    //private final String SERVER_URL = "http://192.168.0.107:8080/";
    private final String SERVER_URL = "https://roomr-222721.appspot.com/";


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

        Gson gson = new Gson();
        String json = gson.toJson(house);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(SERVER_URL + "AddHouse/" + "abc")
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


    public void getLandlordHouses(Landlord landlord){

        Gson gson = new Gson();
        String json = gson.toJson(landlord);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(SERVER_URL + "GetHouse/" + "abc")
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

    public void signUpLandlord(final Landlord landlord){

        Gson gson = new Gson();
        String json = gson.toJson(landlord);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(SERVER_URL + "SignUpTempLandlord")
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

    public void getLandlord(Login login) {
        final Gson gson = new Gson();
        String json = gson.toJson(login);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(SERVER_URL + "GetLandlord")
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

    public void getTenant(Login login) {
        final Gson gson = new Gson();
        String json = gson.toJson(login);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(SERVER_URL + "GetTenant")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()){
                    fragmentEventListener.update(response.body().string());
                        //Tenant tenant = gson.fromJson(response.body().string(), Tenant.class);


                }
                response.close();

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }
    public void addProfile(Profile profile) {
        final Gson gson = new Gson();
        String json = gson.toJson(profile);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(SERVER_URL + "AddProfile")
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


    public void postListing(House house) {
        final Gson gson = new Gson();
        String json = gson.toJson(house);
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(SERVER_URL + "PostListing")
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

    public void getTenantListings() {
        Request request = new Request.Builder()
                .url(SERVER_URL + "ProfileListings")
                .get()
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
                .url("http://10.16.24.254:8080/" + "AddPhoto")
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
                .url("http://10.16.24.254:8080/" + "AddRepair")
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

    public void getRepairs(){
        Request request = new Request.Builder()
                .url("http://10.16.24.254:8080/" + "GetRepairs")
                .get()
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

        final Gson gson = new Gson();
        String json = gson.toJson(profile);
        RequestBody body = RequestBody.create(JSON, json);


        Request request = new Request.Builder()
                .url(SERVER_URL + "ContactLandlord")
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

    public void searchListing(Search search) {

        String json = search.convertToJSON();
        RequestBody body = RequestBody.create(JSON, json);


        Request request = new Request.Builder()
                .url(SERVER_URL + "SearchListings")
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

    public void contactProfile(Profile profile) {

        String json = profile.convertToJSON();
        RequestBody body = RequestBody.create(JSON, json);


        Request request = new Request.Builder()
                .url(SERVER_URL + "ContactProfile")
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

    public void updateRepair(Repair repair) {
        final Gson gson = new Gson();
        String json = gson.toJson(repair);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http:/10.16.24.254:8080/" + "UpdateRepairs")
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

    public void convertProfileToTenant(Profile profile) {
        final Gson gson = new Gson();
        String json = gson.toJson(profile);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(SERVER_URL + "ConvertProfileToTenant")
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
