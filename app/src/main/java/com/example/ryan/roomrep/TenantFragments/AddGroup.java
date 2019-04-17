package com.example.ryan.roomrep.TenantFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.MainActivityLandlord;
import com.example.ryan.roomrep.R;



public class AddGroup extends Fragment {



    String[]  chatList ={"Andy"};
    ListView list;
    Button addgroup;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_group, container, false);
        list = view.findViewById(R.id.groupMember);
        addgroup = view.findViewById(R.id.button4);
        addgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Successfully Added",
                        Toast.LENGTH_LONG).show();
                ((MainActivityLandlord) getActivity()).setViewPager(8);
            }
        });
        addpeople();

        return view;
    }

    private void addpeople() {
        ArrayAdapter<String> adapter =new ArrayAdapter<>(
                getActivity(),android.R.layout.simple_list_item_1,chatList
        );
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        //       EnemyActivity.this, android.R.layout.simple_list_item_1, enemyList);


        list.setAdapter(adapter);
    }



}
