package com.example.ryan.roomrep.LandlordFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.Utility;
import com.example.ryan.roomrep.R;
import com.mysql.jdbc.Util;

public class UtilityDialogFragment extends DialogFragment implements SetUtilityDialogActionListener {

    private final static int MAX_UTILITY = 1000;

    EditText edtName;
    SeekBar skbAmount;
    TextView txtSkbOutput;
    Spinner spnFrequency;
    Button btnAddUtility;
    UtilityDialogActionListener dialogActionListener;

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.utility_dialog_fragment, container, false);
        edtName = view.findViewById(R.id.edtAddUtilityName);
        skbAmount = view.findViewById(R.id.skbAddUtility);
        txtSkbOutput = view.findViewById(R.id.txtAddUtilitySkbOutput);
        spnFrequency = view.findViewById(R.id.spnAddUtilitiesPaymentFrequency);
        btnAddUtility = view.findViewById(R.id.btnAddUtilityAddUtility);
        skbAmount.setOnSeekBarChangeListener(onSeekBarAmount);
        skbAmount.setMax(MAX_UTILITY);
        skbAmount.setProgress(MAX_UTILITY/2);



        btnAddUtility.setOnClickListener(onAddUtility);


        return view;
    }


    private SeekBar.OnSeekBarChangeListener onSeekBarAmount = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            txtSkbOutput.setText(Integer.toString(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };





    private View.OnClickListener onAddUtility = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (dialogActionListener != null) {
                Utility utility = new Utility("Hydro", 25.0, "Weekly");
                dialogActionListener.setUtility(utility);
                getDialog().dismiss();
            }

        }
    };


    @Override
    public void setDialogActionListener(UtilityDialogActionListener dialogActionListener) {
        this.dialogActionListener = dialogActionListener;
    }
}
