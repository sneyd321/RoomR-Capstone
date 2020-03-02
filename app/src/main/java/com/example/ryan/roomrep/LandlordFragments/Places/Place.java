package com.example.ryan.roomrep.LandlordFragments.Places;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;


public class Place {

    private PlacesClient placesClient;
    private AutocompleteSessionToken token;
    private RectangularBounds bounds;
    private Context context;


    public Place(Context context) {
        this.context = context;
        initPlacesClient();
    }

    private void initPlacesClient() {
        try {
            ApplicationInfo app = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = app.metaData;
            Places.initialize(context, bundle.getString("com.google.android.libraries.places.API_KEY"));
            this.placesClient = Places.createClient(context);
            token = AutocompleteSessionToken.newInstance();
            bounds = RectangularBounds.newInstance(new LatLng(41.66, -95.16), new LatLng(56.86, -74.34));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public FindAutocompletePredictionsRequest getAutoCompleteRequest(String query) {
        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setLocationBias(bounds)
                .setCountry("ca")
                .setTypeFilter(TypeFilter.ADDRESS)
                .setSessionToken(token)
                .setQuery(query)
                .build();

        return request;
    }

    public PlacesClient getPlacesClient() {
        return placesClient;
    }
}
