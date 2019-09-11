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
import com.example.ryan.roomrep.Adapters.RepairRecyclerViewAdapter;
import com.example.ryan.roomrep.Classes.ChatMessage;
import com.example.ryan.roomrep.Classes.Repair;
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
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nullable;

import static android.support.constraint.Constraints.TAG;


public class MessagRFragment extends Fragment
        //implements MessagrRecycleViewAdapter.ItemClickListener
        {
   // public static final String MESSAGE_KEY = "message";
    //private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("communication/message");
    //FirebaseFirestore mDocRef = FirebaseFirestore.getInstance();
   MessagrRecycleViewAdapter adapter;
   //private static final String TAG = "MessagrRecyclerView";
    RecyclerView messageList;
    ChatMessage messageSend;
    //ArrayList<String> messages;
    ArrayList<ChatMessage> infMessages = new ArrayList<>();
    Button send;
    EditText editText;
    //String chatRoomName;
            //String chatRoomName = "TheTestOne";
    //TextView userName;
    //TextView time;
    //ChatMessage inputInfo;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

            @Override
            public void onStart() {
                super.onStart();
                String chatRoomName = ((MainActivityTenant)getActivity()).chatRoomNameInMainActivityTenant;
                        // ((MainActivityTenant)getActivity()).getRepair().add(repair);
                db.collection("Test2").document("ChatRoom").collection(chatRoomName).addSnapshotListener(new EventListener<QuerySnapshot>() {
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




        //messages = new ArrayList<>();
        //infMessages = new ArrayList<>();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messag_r, container, false);



        send = view.findViewById(R.id.sendButton);
        //send.setOnClickListener(onSend);
        editText = view.findViewById(R.id.editText);
        //userName = view.findViewById(R.id.message_user);
        //time = view.findViewById(R.id.message_time);
        messageList = view.findViewById(R.id.recyclerRepairsView);
        messageList.setLayoutManager(new LinearLayoutManager(getActivity()));
        //adapter = new MessagrRecycleViewAdapter(getActivity(), messages);
        //adapter = new MessagrRecycleViewAdapter(getActivity(),infMessages);
        ////adapter.setClickListener(this);


        messageList.setItemAnimator(new DefaultItemAnimator());

        //DocumentReference docRef = db.collection("cities").document("BJ");
        //mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
         //   @Override
         //   public void onSuccess(DocumentSnapshot documentSnapshot) {
          //      //City city = documentSnapshot.toObject(City.class);
           //     inputInfo = documentSnapshot.toObject(ChatMessage.class);
                //Toast.makeText(getActivity(), inputInfo.getMessageUser(), Toast.LENGTH_LONG).show();
            //    messages.add(inputInfo.getMessageText());
                //userName.setText("Andy");
              //  adapter.notifyDataSetChanged();
            //}
        //});
        //infMessages.clear();
        //getMessageFromDataBase();



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chatRoomName = ((MainActivityTenant)getActivity()).chatRoomNameInMainActivityTenant;

                            if(!editText.getText().toString().equals("")){
                                //Toast.makeText(getActivity(), editText.getText().toString(), Toast.LENGTH_SHORT).show();
                messageSend= new ChatMessage();

                messageSend.setMessageUser("FATHER");
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







    //public void saveMessage(){
    //    String message = editText.getText().toString();
    //    ChatMessage infoMessage = new ChatMessage(message,"Ryan");
    //    if (message.isEmpty()){return;}
        //mDocRef.set(infoMessage);
    //}

    //@Override
    //public void onItemClick(View view, int position) {

    //}


//    View.OnClickListener onSend = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//
//
//            if(!editText.getText().toString().equals("")){
//                messageSend.setMessageUser("FATHER");
//                messageSend.setMessageText(editText.getText().toString());
//
//                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
//                DateFormat time = new SimpleDateFormat("hh:mm:ss a");
//                String formatTime = time.format(new Date());
//                messageSend.setMessageTime(date + " " + formatTime);
//
//
//                sendDataTodataBase();
//            }else{
//                Toast.makeText(getActivity(), "message can not be empty", Toast.LENGTH_SHORT).show();
//            }
//            //saveMessage();
//
//
//
//            //messages.add(editText.getText().toString());
//            //userName.setText("Andy");
//
//            //adapter.notifyDataSetChanged();
//
//            editText.setText("");
//
//
//        }
//    };

    public void getMessageFromDataBase(){
        String chatRoomName = ((MainActivityTenant)getActivity()).chatRoomNameInMainActivityTenant;
        Task<QuerySnapshot> result = db.collection("Test2").document("ChatRoom").collection(chatRoomName).get();
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

            private void sendDataTodataBase(){
                //Task<Void> result = messageSend.addValues();
                Task<Void> result = messageSend.addNewValues();
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


}
