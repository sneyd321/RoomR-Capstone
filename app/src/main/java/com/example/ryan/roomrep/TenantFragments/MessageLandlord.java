package com.example.ryan.roomrep.TenantFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.ChatMessage;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;


public class MessageLandlord extends Fragment {

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("communication/message2");
    public static final String MESSAGE_KEY = "message";
     ListView list;
    ChatMessage globalMessage;
    FloatingActionButton fab;
    EditText userInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message_landlord, container, false);
        list = view.findViewById(R.id.list_of_messages);
        //View view = inflater.inflate(R.layout.fragment_messag_r, container, false);
         fab = view.findViewById(R.id.fabLOL);
          userInput= view.findViewById(R.id.inputdataLOL);

        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //City city = documentSnapshot.toObject(City.class);
                globalMessage = documentSnapshot.toObject(ChatMessage.class);
                List<ChatMessage> messages = new ArrayList<ChatMessage>();
                messages.add(globalMessage);

            }
        });






        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = userInput.getText().toString();
                //ChatMessage infoMessage = new ChatMessage(message, "Ryan");
                if (message.isEmpty()) {
                    return;
                }
                ;
                //mDocRef.set(infoMessage);
                userInput.setText("");
                //db.collection("cities").document("LA").set(city);
                //Map<String, Object> dataToSave =new HashMap<String, Object>();
                //dataToSave.put(MESSAGE_KEY,new ChatMessage(message,"Fucker"));
                //dataToSave.put(MESSAGE_KEY,message);
                //mDocRef.set(infoMessage).addOnSuccessListener(new OnSuccessListener<Void>() {
                //  @Override
                // public void onSuccess(Void aVoid) {
                //   Toast.makeText(getActivity(),"success to read data",Toast.LENGTH_LONG).show();
                // Log.wtf(TAG,"ERROR---------------------------------------------Failed to save");

                //}
                //}).addOnFailureListener(new OnFailureListener() {
                //  @Override
                // public void onFailure(@NonNull Exception e) {
                //   Toast.makeText(getActivity(),"fail to read data",Toast.LENGTH_LONG).show();
                //  Log.wtf(TAG,"ERROR---------------------------------------------Failed to save", e);

                //}
                //});
                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database


                // Clear the input

            }
        });
        return view;

    }


}






