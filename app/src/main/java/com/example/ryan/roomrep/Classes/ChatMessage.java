package com.example.ryan.roomrep.Classes;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChatMessage {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String messageText;
    private String messageUser;
    private String messageTime;
    private String chatRoomName;
    private boolean isSuccessful;


    public ChatMessage(String messageText, String messageUser, String messageTime) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.messageTime = messageTime;

    }

    public ChatMessage(String messageText, String messageUser, String messageTime, String chatRoomName){
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.messageTime = messageTime;
        this.chatRoomName = chatRoomName;
    }

    public ChatMessage(){

    }






    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getChatRoomName(){return  chatRoomName;}

    public void setChatRoomName(String chatRoomName){this.chatRoomName = chatRoomName;}

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public Task<Void> addValues(){
        final Map<String, Object> message = new HashMap<>();
        message.put("messageText", this.messageText);
        message.put("messageTime", this.messageTime);
        message.put("messageUser", this.messageUser);


        final ChatMessage chatMessage = this;
        return db.collection("Test").document(this.messageTime)
                .set(message)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        chatMessage.setSuccessful(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        chatMessage.setSuccessful(false);
                    }
                });
    }

    public Task<Void> addNewValues(){
        final Map<String, Object> message = new HashMap<>();
        message.put("messageText", this.messageText);
        message.put("messageTime", this.messageTime);
        message.put("messageUser", this.messageUser);


        final ChatMessage chatMessage = this;
        return db.collection("Test2").document("ChatRoom").collection(chatRoomName).document(this.messageTime)
                .set(message)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        chatMessage.setSuccessful(true);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        chatMessage.setSuccessful(false);
                    }
                });
    }


    //public Task<QuerySnapshot> GetMessage(){

        //return db.collection("Test").get();
    //}


}
