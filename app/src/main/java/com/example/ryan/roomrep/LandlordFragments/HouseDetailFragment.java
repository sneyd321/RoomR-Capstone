package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ryan.roomrep.Adapters.HouseDetailUIRecyclerViewAdapter;
import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.App.UI.HouseDetail;
import com.example.ryan.roomrep.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HouseDetailFragment extends Fragment implements ItemClickListener {


    RecyclerView rcyHouseDetail;
    List<HouseDetail> houseDetails;
    HouseDetailUIRecyclerViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house_detail_house_detail, container, false);
        rcyHouseDetail = view.findViewById(R.id.rcyHouseDetailHouseDetails);

        adapter = new HouseDetailUIRecyclerViewAdapter(getActivity(), houseDetails);
        rcyHouseDetail.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter.setItemClickListener(this);
        rcyHouseDetail.setAdapter(adapter);

        return view;
    }


    @Override
    public void onItemClick(View view, int position) {
        HouseDetail houseDetail = adapter.getItemAtPosition(position);
        switch (houseDetail.getName()){
            case "Edit Rental Unit Location":
                NavHostFragment.findNavController(HouseDetailFragment.this).navigate(R.id.action_houseDetailStatePagerFragment_to_updateLocationFragment);
                break;
            case "Generate Lease":
                NavHostFragment.findNavController(HouseDetailFragment.this).navigate(R.id.action_houseDetailStatePagerFragment_to_generateLeaseFragment);
            default:
                Toast.makeText(getActivity(), "Not implemented for release: 1", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    public HouseDetailFragment setData(List<HouseDetail> houseDetails) {
        this.houseDetails = houseDetails;
        return this;
    }
}
