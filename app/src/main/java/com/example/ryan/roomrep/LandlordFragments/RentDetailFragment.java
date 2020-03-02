package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.Adapters.ItemClickListener;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RentDetailFragment extends Fragment implements ItemClickListener {


    RecyclerView rcyHouseDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house_detail_house_detail, container, false);
        rcyHouseDetail = view.findViewById(R.id.rcyHouseDetailHouseDetails);


        return view;
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                NavHostFragment.findNavController(RentDetailFragment.this).navigate(R.id.action_houseDetailStatePagerFragment_to_rentDetailAddUtilityFragment);
                break;

        }
    }
}
