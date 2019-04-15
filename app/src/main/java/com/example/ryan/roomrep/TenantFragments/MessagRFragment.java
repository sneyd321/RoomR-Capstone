package com.example.ryan.roomrep.TenantFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.Adapters.MessagrRecycleViewAdapter;
import com.example.ryan.roomrep.Classes.ChatMessage;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;


public class MessagRFragment extends Fragment implements MessagrRecycleViewAdapter.ItemClickListener{
    public static final String MESSAGE_KEY = "message";
    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("communication/message");
    //FirebaseFirestore mDocRef = FirebaseFirestore.getInstance();
    MessagrRecycleViewAdapter adapter;
    RecyclerView messageList;
    ArrayList<String> messages;
    Button send;
    EditText editText;
    TextView userName;
    TextView time;
    ChatMessage inputInfo;


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
        //userName = view.findViewById(R.id.message_user);
        //time = view.findViewById(R.id.message_time);
        messageList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MessagrRecycleViewAdapter(getActivity(), messages);

        ////adapter.setClickListener(this);
        messageList.setAdapter(adapter);
        messageList.setItemAnimator(new DefaultItemAnimator());
        //DocumentReference docRef = db.collection("cities").document("BJ");
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //City city = documentSnapshot.toObject(City.class);
                inputInfo = documentSnapshot.toObject(ChatMessage.class);
                //Toast.makeText(getActivity(), inputInfo.getMessageUser(), Toast.LENGTH_LONG).show();
                messages.add(inputInfo.getMessageText());
                //userName.setText("Andy");
                adapter.notifyDataSetChanged();
            }
        });


        return view;




    }

    public void saveMessage(){
        String message = editText.getText().toString();
        ChatMessage infoMessage = new ChatMessage(message,"Ryan");
        if (message.isEmpty()){return;}
        mDocRef.set(infoMessage);
    }

    @Override
    public void onItemClick(View view, int position) {

    }


    View.OnClickListener onSend = new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            saveMessage();



            messages.add(editText.getText().toString());
            //userName.setText("Andy");
            adapter.notifyDataSetChanged();

            editText.setText("");


        }
    };


}
