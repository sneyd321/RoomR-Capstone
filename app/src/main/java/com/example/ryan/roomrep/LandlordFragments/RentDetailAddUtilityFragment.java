package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.Adapters.UtilityRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.House.House;
import com.example.ryan.roomrep.Classes.House.Utility.Utility;
import com.example.ryan.roomrep.Classes.HouseViewModel;
import com.example.ryan.roomrep.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RentDetailAddUtilityFragment extends Fragment {


    RecyclerView rcyAddUtility;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_utilities, container, false);
        rcyAddUtility = view.findViewById(R.id.rcyAddUtility);

        HouseViewModel viewModel = ViewModelProviders.of(getActivity()).get(HouseViewModel.class);
        House house = viewModel.getSelected().getValue();

        List<Utility> utilityList = new ArrayList<>();
        utilityList.add(new Utility("Hydro", 0, "Weekly", "Tenant"));
        utilityList.add(new Utility("Internet", 0, "Weekly", "Tenant"));
        utilityList.add(new Utility("Cable", 0, "Weekly", "Tenant"));
        utilityList.add(new Utility("Phone Line", 0, "Weekly", "Tenant"));


        rcyAddUtility.setLayoutManager(new LinearLayoutManager(getActivity()));
        UtilityRecyclerviewAdapter adapter = new UtilityRecyclerviewAdapter(getActivity(), utilityList);
        rcyAddUtility.setAdapter(adapter);




        return view;
    }
}
