package com.example.ryan.roomrep.TenantFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;


public class ExpertSystemFragment extends Fragment {


    Button yes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expert_system, container, false);

        yes = view.findViewById(R.id.button);

        yes.setOnClickListener(onYes);


        return view;
    }

    View.OnClickListener onYes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivityTenant)getActivity()).setViewPager(7);
        }
    };


}
