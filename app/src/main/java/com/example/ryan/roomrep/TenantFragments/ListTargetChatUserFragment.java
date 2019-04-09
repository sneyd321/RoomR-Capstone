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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;


public class ListTargetChatUserFragment extends Fragment {

    String[]  chatList ={"Group chat","mike"};

    private ListView list;
    private TextView playName;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_target_chat_user, container, false);
        //return inflater.inflate(R.layout.fragment_list_target_chat_user, container, false);
        list = view.findViewById(R.id.chatList);
        addpeople();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ((MainActivityTenant)getActivity()).setViewPager(9);
            }
        });
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
