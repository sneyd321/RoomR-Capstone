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
import android.widget.Toast;

import com.example.ryan.roomrep.Adapters.MessagrRecycleViewAdapter;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
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

    //public void saveMessage(){
    //    String message = editText.getText().toString();
    //    if (message.isEmpty()){return;}
    //    Map<String, Object> dataToSave =new HashMap<String, Object>();
    //    dataToSave.put(MESSAGE_KEY,message);
    //    mDocRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
    //        @Override
    //        public void onSuccess(Void aVoid) {
    //            Toast.makeText(getActivity(),"success to read data",Toast.LENGTH_LONG).show();
                //Log.wtf(TAG,"ERROR---------------------------------------------Failed to save");

            //}
        //}).addOnFailureListener(new OnFailureListener() {
            //@Override
            //public void onFailure(@NonNull Exception e) {
              //  Toast.makeText(getActivity(),"fail to read data",Toast.LENGTH_LONG).show();
                //Log.wtf(TAG,"ERROR---------------------------------------------Failed to save", e);

           // }
        //});
    //}

    @Override
    public void onItemClick(View view, int position) {

    }


    View.OnClickListener onSend = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            messages.add(editText.getText().toString());
            adapter.notifyDataSetChanged();
            //saveMessage();

            String message = editText.getText().toString();
            Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
                if (message.isEmpty()){return;}
            Toast.makeText(getActivity(),"1111",Toast.LENGTH_LONG).show();
            Map<String, Object> dataToSave =new HashMap<String, Object>();
            //Map<String, Object> user = new HashMap<>();
            //user.put("first", "Ada");
            //user.put("last", "Lovelace");
            //user.put("born", 1815);
            Toast.makeText(getActivity(),"2222",Toast.LENGTH_LONG).show();
                dataToSave.put(MESSAGE_KEY,message);
            Toast.makeText(getActivity(),"3333",Toast.LENGTH_LONG).show();
            //mDocRef.collection("users")
                    //.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            //.add(message).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                //@Override
                //public void onSuccess(DocumentReference documentReference) {
                  //  Toast.makeText(getActivity(),"4444",Toast.LENGTH_LONG).show();
                    //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                //}
            //})
              //      .addOnFailureListener(new OnFailureListener() {
               //         @Override
               //         public void onFailure(@NonNull Exception e) {
               //             Toast.makeText(getActivity(),"5555",Toast.LENGTH_LONG).show();
               //             Log.w(TAG, "Error adding document", e);
                 //       }
                 //   });



            mDocRef.set(dataToSave).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(),"success to read data",Toast.LENGTH_LONG).show();
            Log.wtf(TAG,"ERROR---------------------------------------------Failed to save");

            }
            }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
              Toast.makeText(getActivity(),"fail to read data",Toast.LENGTH_LONG).show();
            Log.wtf(TAG,"ERROR---------------------------------------------Failed to save", e);

             }
            });
            editText.setText("");


        }
    };


}
