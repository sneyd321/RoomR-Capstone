package com.example.ryan.roomrep.LandlordFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ryan.roomrep.Adapters.MessagrRecycleViewAdapter;
import com.example.ryan.roomrep.Classes.ChatMessage;
import com.example.ryan.roomrep.Classes.Router.LandlordRouter;
import com.example.ryan.roomrep.Classes.Router.LandlordRouterAction;
import com.example.ryan.roomrep.MainActivityLandlord;
import com.example.ryan.roomrep.MainActivityTenant;
import com.example.ryan.roomrep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nullable;

import static android.support.constraint.Constraints.TAG;


public class MessageLandlordFragment extends Fragment {

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("communication/message2");
    public static final String MESSAGE_KEY = "message";
    MessagrRecycleViewAdapter adapter;
    LandlordRouterAction routerActionListener;
    RecyclerView messageList;
    ChatMessage messageSend;
    //ArrayList<String> messages;
    ArrayList<ChatMessage> infMessages = new ArrayList<>();
    Button send;
    EditText editText;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onStart() {
        super.onStart();
        String chatRoomName = ((MainActivityLandlord)getActivity()).chatRoomNameInMainActivityLandlord;
        String roomType = ((MainActivityLandlord)getActivity()).chatRoomType;
        // ((MainActivityTenant)getActivity()).getRepair().add(repair);
        db.collection("Messages").document(roomType).collection(chatRoomName).addSnapshotListener(new EventListener<QuerySnapshot>() {
            //db.collection("Test").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }
                infMessages.clear();
                //messageList.scrollToPosition ( infMessages.size ()-1 );
                getMessageFromDataBase();

            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message_landlord, container, false);


        send = view.findViewById(R.id.sendButton2);
        //send.setOnClickListener(onSend);
        editText = view.findViewById(R.id.editText2);
        //userName = view.findViewById(R.id.message_user);
        //time = view.findViewById(R.id.message_time);
        messageList = view.findViewById(R.id.recyclerLandlordMessageView);
        messageList.setLayoutManager(new LinearLayoutManager(getActivity()));
        messageList.setItemAnimator(new DefaultItemAnimator());

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chatRoomName = ((MainActivityLandlord)getActivity()).chatRoomNameInMainActivityLandlord;
                String peopleName = ((MainActivityLandlord)getActivity()).chatPeopleName;
                if(!editText.getText().toString().equals("")){
                    //Toast.makeText(getActivity(), editText.getText().toString(), Toast.LENGTH_SHORT).show();
                    messageSend= new ChatMessage();

                    messageSend.setMessageUser(peopleName);
                    messageSend.setMessageText(editText.getText().toString());
                    messageSend.setChatRoomName(chatRoomName);

                    String sendDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    DateFormat sendTime = new SimpleDateFormat("hh:mm:ss ");
                    String formatTime = sendTime.format(new Date());
                    messageSend.setMessageTime(sendDate + " " + formatTime);


                    sendDataTodataBase();


                    editText.setText("");
                }else{
                    Toast.makeText(getActivity(), "message can not be empty", Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();

                editText.setText("");


            }
        });

        return view;

    }

    private void sendDataTodataBase() {
        String roomType = ((MainActivityLandlord)getActivity()).chatRoomType;
        //Task<Void> result = messageSend.addValues();
        Task<Void> result = messageSend.addNewValues(roomType);
        result.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(messageSend.isSuccessful()){
                    Toast.makeText(getActivity(),"Message sent", Toast.LENGTH_SHORT).show();

                }
            }
        });

        adapter.notifyDataSetChanged();
        infMessages.clear();
    }

    public void setRouterAction(LandlordRouter landlordRouter) {
        this.routerActionListener = landlordRouter;
    }

    private void getMessageFromDataBase() {
        String chatRoomName = ((MainActivityLandlord)getActivity()).chatRoomNameInMainActivityLandlord;
        String roomType = ((MainActivityLandlord)getActivity()).chatRoomType;
        Task<QuerySnapshot> result = db.collection("Messages").document(roomType).collection(chatRoomName).get();
        //Task<QuerySnapshot> result =  db.collection("Test").get();
        //Task<QuerySnapshot> result =  db.collection("Repair").get();
        //Request all the documents in the data collection "communication"
        result.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //Log.d(TAG, document.getId() + " => " + document.getData());

                        ChatMessage chatMessage = new ChatMessage();
                        //Toast.makeText(getActivity(), document.get("messageText").toString(), Toast.LENGTH_SHORT).show();
                        chatMessage.setMessageText(document.get("messageText").toString());
                        chatMessage.setMessageTime(document.get("messageTime").toString());
                        chatMessage.setMessageUser(document.get("messageUser").toString());
                        ChatMessage tryMessage = new ChatMessage(document.get("messageText").toString(),document.get("messageUser").toString(),document.get("messageTime").toString());
                        infMessages.add(tryMessage);
//                        messageList.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
                    }


                    adapter = new MessagrRecycleViewAdapter(getActivity(),infMessages);
                    messageList.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                }
            }
        });

    }


}






