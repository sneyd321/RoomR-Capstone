package com.example.ryan.roomrep.Classes.Network;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.LandlordFragments.AddHouseFragment;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Network {

    private final String SERVER_URL = "http://192.168.0.102:8080/";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private AddHouseListener addHouseListener;

    public void registerAddHouseListener(AddHouseListener addHouseListener){
        this.addHouseListener = addHouseListener;
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

}
