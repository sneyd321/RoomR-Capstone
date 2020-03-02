package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ryan.roomrep.Classes.Location.Location;
import com.example.ryan.roomrep.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

public class UpdateLocationFragment extends Fragment {

    EditText edtAddress, edtCity, edtProvince, edtPostalCode;
    Button btnUpdatLocation;

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




        return view;
    }


    private View.OnClickListener onUpdateLocation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            NavHostFragment.findNavController(UpdateLocationFragment.this).navigate(R.id.action_updateLocationFragment_to_houseDetailStatePagerFragment);
        }
    };

}
