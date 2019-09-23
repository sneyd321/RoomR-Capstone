package com.example.ryan.roomrep.TenantFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;
import com.example.ryan.roomrep.R;
import com.google.firebase.storage.FirebaseStorage;

public class SendRepairFragment extends Fragment {
    private FirebaseStorage storage = FirebaseStorage.getInstance();

    Button btn_send;
    TextView txt_category;
    TextView txt_desciption;
    EditText edt_description;
    Repair repair;

    //texviews for the english words and other language words
    TextView txt_english1;
    TextView txt_english2;
    TextView txt_english3;
    TextView txt_english4;
    TextView txt_english5;
    TextView txt_otherLanguage1;
    TextView txt_otherLanguage2;
    TextView txt_otherLanguage3;
    TextView txt_otherLanguage4;
    TextView txt_otherLanguage5;


    private TenantRouterAction actionListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_send_repair, container, false);

        btn_send = view.findViewById(R.id.btn_sendProblem);
        txt_category = view.findViewById(R.id.txt_category);
        edt_description = view.findViewById(R.id.edt_description);
        txt_desciption = view.findViewById(R.id.txt_descriptionLabel);
        btn_send.setOnClickListener(onSendProblemToDB);

        //TextViews for english and other language words.
        txt_english1 = view.findViewById(R.id.txt_english1);
        txt_english2 = view.findViewById(R.id.txt_english2);
        txt_english3 = view.findViewById(R.id.txt_english3 );
        txt_english4 = view.findViewById(R.id.txt_english4);
        txt_english5 = view.findViewById(R.id.txt_english5);

        txt_otherLanguage1 = view.findViewById(R.id.txt_otherLanguage1);
        txt_otherLanguage2 = view.findViewById(R.id.txt_otherLanguage2);
        txt_otherLanguage3 = view.findViewById(R.id.txt_otherLanguage3);
        txt_otherLanguage4 = view.findViewById(R.id.txt_otherLanguage4);
        txt_otherLanguage5 = view.findViewById(R.id.txt_otherLanguage5);

        setView();
        return view;
    }

    private void setView(){
        //Populate all the english and other words language
    }
    View.OnClickListener onSendProblemToDB = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            //Here the method sends the problem detected to the next page. and launches sendProblem.
            if(!edt_description.getText().toString().equals("")){
                repair.setDescription(edt_description.getText().toString());
                saveRepair();
            }else{
                Toast.makeText(getActivity(), "Please write a description", Toast.LENGTH_SHORT).show();
            }
        }
    };


    private void saveRepair(){
        //Adds values to the firestorage.

    }

    public void setActionListener(TenantRouterAction actionListener) {
        this.actionListener = actionListener;
        //Use this actionListener to later manage the back stack and pop off this view and the other view and re-direct to viewRepairList.
    }
}
