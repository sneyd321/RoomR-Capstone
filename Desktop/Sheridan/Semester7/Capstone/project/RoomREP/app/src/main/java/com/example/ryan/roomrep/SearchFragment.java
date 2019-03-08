package com.example.ryan.roomrep;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SearchFragment extends Fragment {


    Button search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        search = view.findViewById(R.id.btnSearchListings);
        search.setOnClickListener(onSearchListings);
        return view;
    }

    private View.OnClickListener onSearchListings = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivityTenant)getActivity()).setViewPager(1);
        }
    };


}
