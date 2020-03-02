package com.example.ryan.roomrep.TenantFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TenantViewListingFragment extends Fragment implements FragmentEventListener {


    TextView txtAddress;
    ImageView imgHouseImage;
    TextView txtRent;
    TextView txtSize;
    TextView txtBaths;
    TextView txtBeds;
    TextView txtDescription;
    TextView txtHydro;
    TextView txtElectrical;
    TextView txtInternet;
    TextView txtPhoneLine;
    TextView txtRating;

    Button btnContactLandlord;
    Button btnGoBack;


    String rating;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenant_view_listings, container, false);

        txtAddress = view.findViewById(R.id.txtTenantViewListingAddress);
        imgHouseImage = view.findViewById(R.id.imgTenantViewListingHouse);
        txtRent = view.findViewById(R.id.txtTenantViewListingRent);
        txtSize = view.findViewById(R.id.txtTenantViewListingSize);
        txtBaths = view.findViewById(R.id.txtTenantViewListingBath);
        txtBeds = view.findViewById(R.id.txtTenantViewListingBed);
        txtDescription = view.findViewById(R.id.txtTenantViewListingDescription);
        txtRating = view.findViewById(R.id.txtLandlordRating);
        txtHydro = view.findViewById(R.id.txtTenantViewListingHydroIncluded);
        txtElectrical = view.findViewById(R.id.txtTenantViewListingElectricalIncluded);
        txtInternet = view.findViewById(R.id.txtTenantViewListingInternetIncluded);
        txtPhoneLine = view.findViewById(R.id.txtTenantViewListingPhoneLineIncluded);
        btnContactLandlord = view.findViewById(R.id.btnTenantViewListingsContactLandlord);
        btnContactLandlord.setOnClickListener(onContactLandlord);
        btnGoBack = view.findViewById(R.id.btnTenantViewListingsGoBack);
        btnGoBack.setOnClickListener(onGoBack);
        //txtAddress.setText(house.getLocation().getAddress());

        //txtRent.setText(Integer.toString(house.getRent().getBaseRent()));

        //txtDescription.setText(house.getDescription());
        txtHydro.setText("Not Included");
        txtElectrical.setText("Not Included");
        txtInternet.setText("Not Included");
        txtPhoneLine.setText("Not Included");
        //txtRating.setText(rating);

        isLandlordAlreadyNotified();






        return view;
    }

    public void setThatBread(String rating){
        this.rating = rating;
    }

    public void  getThatbread(String address){
        Network network = new Network();
        network.registerObserver(this);
        network.getLandlordRating(address);
    }

    private void isLandlordAlreadyNotified() {
        List<Profile> profiles = new ArrayList<>();
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String email = (sharedPref.getString("ProfileEmail", ""));

        for (Profile profile : profiles) {
            if (profile.getEmail().equals(email)){
                btnContactLandlord.setVisibility(View.INVISIBLE);
            }
        }
    }

    View.OnClickListener onGoBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener onContactLandlord = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Network network = Network.getInstance();
            network.registerObserver(TenantViewListingFragment.this);
            SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
            String firstName = (sharedPref.getString("ProfileFirstName", ""));
            String lastName = (sharedPref.getString("ProfileLastName", ""));
            String email = (sharedPref.getString("ProfileEmail", ""));
            String bio = (sharedPref.getString("ProfileBio", ""));
            Profile profile = new Profile(firstName, lastName, email, bio);
            network.contactLandlord(profile);
            Toast.makeText(getActivity(), "Landlord Notified", Toast.LENGTH_SHORT).show();
            btnContactLandlord.setVisibility(View.INVISIBLE);

        }
    };




    @Override
    public void update(String response) {
        if (response.equals("")){
            return;
        }
        else{
            try {
                JSONObject jsonObject = new JSONObject(response);
                rating = jsonObject.get("repairrating").toString();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtRating.setText(rating);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}
