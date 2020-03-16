package com.example.ryan.roomrep.Classes.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.ryan.roomrep.Classes.App;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Network<T> implements NetworkObservable {

    //private final String SERVER_URL2 = "http://10.16.25.62:8080/";
    //private final String SERVER_URL = "https://roomr-222721.appspot.com/";
    private final String SERVER_URL = "http://192.168.0.106:8080/v1/";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private static Network instance = null;

    public static Network getInstance() {
        if (instance == null) {
            instance = new Network();
        }
        return instance;
    }

    private NetworkObserver networkObserver;
    private Gson gson;
    private OkHttpClient client;
    RequestBody body;
    HttpUrl.Builder urlBuilder;

    private Network() {
        gson = new Gson();
        client = new OkHttpClient();
        urlBuilder = HttpUrl.parse(SERVER_URL).newBuilder();
    }

    public boolean isNetworkAvailable() {
        final ConnectivityManager cm = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public void registerObserver(NetworkObserver networkObserver) {
        this.networkObserver = networkObserver;
    }

    @Override
    public void clearObserver() {
       this.networkObserver = null;
    }

    @Override
    public void notifyObserver(String response) {
        if (networkObserver != null){
            this.networkObserver.update(response);
            clearObserver();
        }

    }

    public void post(final T t, String route) {
        String json = gson.toJson(t);
        body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(SERVER_URL + route)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                onSuccess(response);
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                onFailed(e);
            }
        });
    }

    public void addQueryParam(String name, String value) {
        urlBuilder.addQueryParameter(name, value);
    }

    public void get(String route) {
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString() + route)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                onSuccess(response);
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                onFailed(e);
            }
        });

    }



    private void onSuccess(Response response) throws IOException{
        switch (response.code()) {
            case 401:
                notifyObserver("Authorization failed.");
                break;
            case 403:
                notifyObserver("Permission denied.");
                break;
            case 404:
                notifyObserver("Invalid API route.");
                break;
            case 409:
                notifyObserver("Conflicting request. Try again later.");
                break;
        }
        if (response.isSuccessful()){
            notifyObserver(formatResponse(response.body().string()));
        }
        response.close();
    }


    private void onFailed(IOException e) {
        if(e instanceof SocketTimeoutException){
            notifyObserver("Socket timeout error");
            return;
        }
        notifyObserver("500 Server error");
    }


    private String formatResponse(String response) {
        switch (getJSONResult(response)) {
            case "House added.":
                return "House added successfully.";
            case "House already exists":
                return "Error: House already exists";
            case "Temporary account created.":
                return  "Temporary account create. Please check email.";
            case "Homeowner already exists.":
                return  "Error: Homeowner account already exists with that email.";
            default:
                return response;
        }
    }


    private String getJSONResult(String response) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(response);
            return jsonObject.getString("Result");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
