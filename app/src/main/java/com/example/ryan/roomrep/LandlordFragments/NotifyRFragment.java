package com.example.ryan.roomrep.LandlordFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.roomrep.Adapters.LandlordPaymentRecyclerviewAdapter;
import com.example.ryan.roomrep.Classes.Landlord.Landlord;
import com.example.ryan.roomrep.R;

public class NotifyRFragment extends Fragment {


    RecyclerView rcyPayments;
    Landlord landlord;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifyr, container, false);

        rcyPayments = view.findViewById(R.id.rcyNotifyRPayments);

        rcyPayments.setLayoutManager(new LinearLayoutManager(getActivity()));
        LandlordPaymentRecyclerviewAdapter adapter = new LandlordPaymentRecyclerviewAdapter(getActivity(), landlord.getPayments());
        rcyPayments.setAdapter(adapter);
        adapter.notifyDataSetChanged();





        return view;

    }


    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

}

