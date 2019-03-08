package com.example.ryan.roomrep;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RepairPictureFragment extends Fragment {

    Button sendPhoto;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_repair_picture, container, false);

        sendPhoto = view.findViewById(R.id.btn_takePic);
        sendPhoto.setOnClickListener(onSendPhoto);


        return view;

    }

    View.OnClickListener onSendPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivityTenant)getActivity()).setViewPager(6);

        }
    };

}
