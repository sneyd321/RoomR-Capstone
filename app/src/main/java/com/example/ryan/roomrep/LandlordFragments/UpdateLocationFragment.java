package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.Location;
import com.example.ryan.roomrep.Classes.HouseViewModel;
import com.example.ryan.roomrep.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

public class UpdateLocationFragment extends Fragment {

    EditText edtAddress, edtCity, edtProvince, edtPostalCode;
    Button btnUpdatLocation;

    HouseViewModel houseViewModel;

    House house;

    private void initUI(View view){
        edtAddress = view.findViewById(R.id.edtUpdateHouseLocationAddress);
        edtCity = view.findViewById(R.id.edtUpdateHouseLocationCity);
        edtProvince = view.findViewById(R.id.edtUpdateHouseLocationProvince);
        edtPostalCode = view.findViewById(R.id.edtUpdateHouseLocationPostalCode);
        btnUpdatLocation = view.findViewById(R.id.btnUpdateHouseLocation);
        btnUpdatLocation.setOnClickListener(onUpdateLocation);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house_detail_update_location, container, false);
        initUI(view);
        houseViewModel = ViewModelProviders.of(getActivity()).get(HouseViewModel.class);
        house = houseViewModel.getSelected().getValue();

        edtAddress.setText(house.getLocation().getAddress());
        edtCity.setText(house.getLocation().getCity());
        edtProvince.setText(house.getLocation().getProvince());
        edtPostalCode.setText(house.getLocation().getPostalCode());


        return view;
    }


    private View.OnClickListener onUpdateLocation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Location location = new Location(edtAddress.getText().toString(),
                    edtCity.getText().toString(),
                    edtProvince.getText().toString(),
                    edtPostalCode.getText().toString());
            house.setLocation(location);
            houseViewModel.update(house);
            NavHostFragment.findNavController(UpdateLocationFragment.this).navigate(R.id.action_updateLocationFragment_to_houseDetailStatePagerFragment);
        }
    };

}
