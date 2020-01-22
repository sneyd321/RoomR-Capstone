package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.Adapters.HouseDetailRecyclerViewAdapter;
import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.HouseDetail;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HouseDetailFragment extends Fragment implements ItemClickListener {


    RecyclerView rcyHouseDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house_detail_house_detail, container, false);
        rcyHouseDetail = view.findViewById(R.id.rcyHouseDetailHouseDetails);

        List<HouseDetail> houseDetails = new ArrayList<>();
        houseDetails.add(new HouseDetail(getResources().getDrawable(R.drawable.ic_my_location_black_24dp), "Edit Location"));
        houseDetails.add(new HouseDetail(getResources().getDrawable(R.drawable.ic_home_black_24dp), "Edit House"));


        rcyHouseDetail.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        HouseDetailRecyclerViewAdapter adapter = new HouseDetailRecyclerViewAdapter(getActivity(), houseDetails);
        adapter.setOnCLickListener(this);
        rcyHouseDetail.setAdapter(adapter);


        return view;
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                NavHostFragment.findNavController(HouseDetailFragment.this).navigate(R.id.action_houseDetailStatePagerFragment_to_updateLocationFragment);
                break;
            case 1:
                NavHostFragment.findNavController(HouseDetailFragment.this).navigate(R.id.action_houseDetailStatePagerFragment_to_editHouseDetailFragment);
                break;
        }
    }
}
