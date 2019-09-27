package com.example.ryan.roomrep.TenantFragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryan.roomrep.Classes.LanguageTranslation;
import com.example.ryan.roomrep.Classes.Network.FragmentEventListener;
import com.example.ryan.roomrep.Classes.Network.Network;
import com.example.ryan.roomrep.Classes.Repair;
import com.example.ryan.roomrep.Classes.Router.TenantRouterAction;
import com.example.ryan.roomrep.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class SendRepairFragment extends Fragment implements FragmentEventListener {

    LanguageTranslation languageTranslation;
    Button btn_send;
    TextView txt_category;
    TextView txt_description;
    EditText edt_description;
    Repair repair;
    ProgressDialog progressDialog;

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
        txt_description = view.findViewById(R.id.txt_descriptionLabel);
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
        List<String> englishWords = languageTranslation.getWordsInEnglish();
        List<String> otherLanguageWords = languageTranslation.getWordsInOtherLanguage();

        txt_english1.setText(englishWords.get(0));
        txt_english2.setText(englishWords.get(1));
        txt_english3.setText(englishWords.get(2));
        txt_english4.setText(englishWords.get(3));
        txt_english5.setText(englishWords.get(4));

        if(!otherLanguageWords.get(0).equals("none")){
            txt_otherLanguage1.setText(otherLanguageWords.get(0));
            txt_otherLanguage2.setText(otherLanguageWords.get(1));
            txt_otherLanguage3.setText(otherLanguageWords.get(2));
            txt_otherLanguage4.setText(otherLanguageWords.get(3));
            txt_otherLanguage5.setText(otherLanguageWords.get(4));
        }
        else {
            txt_otherLanguage1.setText("");
            txt_otherLanguage2.setText("");
            txt_otherLanguage3.setText("");
            txt_otherLanguage4.setText("");
            txt_otherLanguage5.setText("");
        }

        txt_category.setText(languageTranslation.getCategory() + " is the Category of Repair");
    }

    View.OnClickListener onSendProblemToDB = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            //Here the method sends the problem detected to the next page. and launches sendProblem.
            if(!edt_description.getText().toString().equals("")){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                String description = edt_description.getText().toString();
                String status = "Unseen";
                String photoRef = languageTranslation.getImgUrl();
                String name = languageTranslation.getCategory();
                String date = dateFormat.format(calendar.getTime());
                repair = new Repair(description,name,date,status,photoRef);
                saveRepair();
            }else{
                Toast.makeText(getActivity(), "Please write a description", Toast.LENGTH_SHORT).show();
            }
        }
    };


    private void saveRepair(){
        Network network = Network.getInstance();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Sending Repair...");
        progressDialog.show();
        network.registerObserver(this);
        network.addRepair(repair);
        Toast.makeText(getContext(), "Repair Sent", Toast.LENGTH_SHORT).show();
        actionListener.onAddRepair(repair);

    }

    public void setActionListener(TenantRouterAction actionListener) {
        this.actionListener = actionListener;
        //Use this actionListener to later manage the back stack and pop off this view and the other view and re-direct to viewRepairList.
    }

    public void setLanguageTranslation(LanguageTranslation languageTranslation){
        this.languageTranslation = languageTranslation;
    }

    @Override
    public void update(String response) {
        progressDialog.dismiss();
    }

}
