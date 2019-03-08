package com.example.ryan.roomrep;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class MessagRFragment extends Fragment implements MessagrRecycleViewAdapter.ItemClickListener{

    MessagrRecycleViewAdapter adapter;
    RecyclerView messageList;
    ArrayList<String> messages;
    Button send;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messag_r, container, false);
        messages = new ArrayList<>();

        messageList = view.findViewById(R.id.rcyMessages);
        send = view.findViewById(R.id.sendButton);
        send.setOnClickListener(onSend);
        editText = view.findViewById(R.id.editText);
        messageList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MessagrRecycleViewAdapter(getActivity(), messages);
        //adapter.setClickListener(this);
        messageList.setAdapter(adapter);
        messageList.setItemAnimator(new DefaultItemAnimator());
        return view;

    }


    @Override
    public void onItemClick(View view, int position) {

    }


    View.OnClickListener onSend = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            messages.add(editText.getText().toString());
            adapter.notifyDataSetChanged();
            editText.setText("");
        }
    };
}
