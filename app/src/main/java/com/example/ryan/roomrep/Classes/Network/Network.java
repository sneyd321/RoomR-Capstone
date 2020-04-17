package com.example.ryan.roomrep.Classes.Network;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.ryan.roomrep.App.App;
import com.example.ryan.roomrep.Classes.Login.Login;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

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

public class Network implements NetworkObservable {

    //private final String SERVER_URL2 = "http://10.16.25.62:8080/";
    //private final String SERVER_URL = "https://roomr-222721.appspot.com/";
    private final String SERVER_URL = "http://192.168.0.108:8080/api/v1/";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private static Network instance = null;

    public static Network getInstance() {
        if (instance == null) {
            instance = new Network();
        }
        return instance;
    }

    private NetworkObserver networkObserver;
    private OkHttpClient client;
    private JSONParser jsonParser;
    private Gson gson;
    private String authToken;


    private Network() {
        client = new OkHttpClient();
        jsonParser = JSONParser.getInstance();
        gson = jsonParser.getGson();
    }

    public boolean isNetworkAvailable(Application application) {
        final ConnectivityManager cm = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
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

    public <T> Request buildPostRequest(T t, String route){
        String json = gson.toJson(t);
        RequestBody body = RequestBody.create(JSON, json);
        return new Request.Builder()
                .url(SERVER_URL + route)
                .header("Authentication", authToken)
                .post(body)
                .build();
    }

    public <T> Request buildPostRequestNoAuth(T t, String route){
        String json = gson.toJson(t);
        RequestBody body = RequestBody.create(JSON, json);
        return new Request.Builder()
                .url(SERVER_URL + route)
                .post(body)
                .build();
    }

    public <T> Request buildPutRequest(T t, String route){
        String json = gson.toJson(t);
        RequestBody body = RequestBody.create(JSON, json);
        return new Request.Builder()
                .url(SERVER_URL + route)
                .header("Authentication", authToken)
                .put(body)
                .build();


    }



    public void login(final Login login) {
        Request request = new Request.Builder()
                .url(SERVER_URL + "login")
                .post(jsonParser.getLoginRequestBody(login))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                handleErrorResponse(response.code());
                if (response.isSuccessful()){
                    authToken = jsonParser.parseLoginToken(response.body().string());
                    notifyObserver("");
                    response.close();
                    return;
                }
                response.close();
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                onFailed(e);
            }
        });
    }

    public void send(Request request) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                handleErrorResponse(response.code());
                if (response.isSuccessful()){
                        notifyObserver("Success");
                        response.close();
                        return;
                }
                response.close();
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                onFailed(e);
            }
        });
    }



    public void getUserData(Login login) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(SERVER_URL).newBuilder();
        urlBuilder.addPathSegment(login.getUserType());
        urlBuilder.addPathSegment(login.getEmail());
        urlBuilder.addQueryParameter("token", authToken);
        Request request =  new Request.Builder()
                .url(urlBuilder.build().toString())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                handleErrorResponse(response.code());
                if (response.isSuccessful()){

                    notifyObserver(response.body().string());
                    response.close();
                    return;
                }
                response.close();
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                onFailed(e);
            }
        });
    }

    private void onFailed(IOException e) {
        if(e instanceof SocketTimeoutException){
            notifyObserver("Socket timeout error");
            return;
        }
        notifyObserver("500: Failed to connect to server.");
    }

    private void handleErrorResponse(int responseCode) {
        switch (responseCode) {
            case 400:
                notifyObserver("400: Bad request.");
                break;
            case 401:
                notifyObserver("401: Unauthorized.");
                break;
            case 403:
                notifyObserver("403: Forbidden.");
                break;
            case 404:
                notifyObserver("404: API route not found.");
                break;
            case 409:
                notifyObserver("409: Conflict, duplicate entry in database.");
                break;
            case 500:
                notifyObserver("500: Internal server error.");
                break;
        }
    }

}
