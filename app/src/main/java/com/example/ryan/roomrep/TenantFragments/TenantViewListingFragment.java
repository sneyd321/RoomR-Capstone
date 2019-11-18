package com.example.ryan.roomrep.TenantFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.Utility;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.Classes.Router.ProfileRouter;
import com.example.ryan.roomrep.Classes.Router.ProfileRouterAction;
import com.example.ryan.roomrep.R;

import com.squareup.picasso.Picasso;

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

    Button btnContactLandlord;
    Button btnGoBack;

    House house;
    ProfileRouterAction routerActionListener;


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
        txtHydro = view.findViewById(R.id.txtTenantViewListingHydroIncluded);
        txtElectrical = view.findViewById(R.id.txtTenantViewListingElectricalIncluded);
        txtInternet = view.findViewById(R.id.txtTenantViewListingInternetIncluded);
        txtPhoneLine = view.findViewById(R.id.txtTenantViewListingPhoneLineIncluded);
        btnContactLandlord = view.findViewById(R.id.btnTenantViewListingsContactLandlord);
        btnContactLandlord.setOnClickListener(onContactLandlord);
        btnGoBack = view.findViewById(R.id.btnTenantViewListingsGoBack);
        btnGoBack.setOnClickListener(onGoBack);
        txtAddress.setText(house.getAddress());
        Picasso.get().load(house.getUrl()).into(imgHouseImage);
        txtRent.setText(Integer.toString(house.getRent()));
        txtSize.setText(Integer.toString(house.getSize()));
        txtBeds.setText(Integer.toString(house.getBedNumber()));
        txtBaths.setText(Integer.toString(house.getBathNumber()));
        txtDescription.setText(house.getDescription());
        txtHydro.setText("Not Included");
        txtElectrical.setText("Not Included");
        txtInternet.setText("Not Included");
        txtPhoneLine.setText("Not Included");

        isLandlordAlreadyNotified();

        for (Utility utility : house.getUtilities()){
            switch (utility.getName()){
                case "Hydro":
                    txtHydro.setText("Included");
                    break;
                case "Electrical":
                    txtElectrical.setText("Included");
                    break;
                case "Internet":
                    txtInternet.setText("Included");
                    break;
                case "Phone Line":
                    txtPhoneLine.setText("Included");
                    break;
            }
        }



        return view;
    }

    private void isLandlordAlreadyNotified() {
        List<Profile> profiles = house.getProfiles();
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
            if (routerActionListener != null) {
                routerActionListener.popBackStack();
            }
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
            profile.setHouseAddress(house.getAddress());
            network.contactLandlord(profile);
            Toast.makeText(getActivity(), "Landlord Notified", Toast.LENGTH_SHORT).show();
            btnContactLandlord.setVisibility(View.INVISIBLE);
            house.addProfile(profile);

        }
    };


    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public void update(String response) {

    }

    public void setRouterAction(ProfileRouterAction routerActionListener) {
        this.routerActionListener = routerActionListener;
    }
}
