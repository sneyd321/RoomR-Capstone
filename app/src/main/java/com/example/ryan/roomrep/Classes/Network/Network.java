package com.example.ryan.roomrep.Classes.Network;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.Classes.Login;
import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.Classes.Tenant;
import com.example.ryan.roomrep.LandlordFragments.AddHouseFragment;
import com.example.ryan.roomrep.TenantFragments.AddProfileFragment;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Network {

    private final String SERVER_URL = "http://10.16.26.209:8080/";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private AddHouseListener addHouseListener;
    private HouseMainListener getHouseListener;
    private LoginListener loginListener;
    private AddProfileListener addProfileListener;
    private PostListingListener postListingListener;

    public void registerAddHouseListener(AddHouseListener addHouseListener){
        this.addHouseListener = addHouseListener;
    }

    public void registerHouseListener(HouseMainListener getHouseListener) {
        this.getHouseListener = getHouseListener;
    }

    public void registerLoginListener(LoginListener loginListener){
        this.loginListener = loginListener;
    }

    public void registerAddProfileListener(AddProfileListener addProfileListener) {
        this.addProfileListener = addProfileListener;
    }

    public void registerPostListingListener(PostListingListener postListingListener){
        this.postListingListener = postListingListener;
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
                    if (addHouseListener != null) {
                        addHouseListener.onAddHouse(response.body().string());
                    }
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
                    if (getHouseListener != null) {
                        try {
                            JSONArray jsonArray = new JSONArray(response.body().string());
                            getHouseListener.onGetHouses(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
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
                    if (loginListener != null){
                        Landlord landlord = gson.fromJson(response.body().string(), Landlord.class);
                        loginListener.onLoginLandlord(landlord);
                    }
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
                    if (loginListener != null){
                        Tenant tenant = gson.fromJson(response.body().string(), Tenant.class);
                        loginListener.onLoginTenant(tenant);
                    }
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
                    if (addProfileListener != null){
                        Profile profile = gson.fromJson(response.body().string(), Profile.class);
                        addProfileListener.onAddProfile(profile);
                    }
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
                    if (postListingListener != null) {
                        postListingListener.onPostListing();
                    }
                }
                response.close();

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }




}
