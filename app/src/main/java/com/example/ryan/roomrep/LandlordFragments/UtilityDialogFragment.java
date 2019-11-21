package com.example.ryan.roomrep.LandlordFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ryan.roomrep.Classes.House.Utility;
import com.example.ryan.roomrep.R;

public class UtilityDialogFragment extends DialogFragment {

    private final static int MAX_UTILITY = 1000;

    Spinner spnName;
    SeekBar skbAmount;
    TextView txtSkbOutput;
    Spinner spnFrequency;
    Button btnAddUtility;
    UtilityDialogActionListener dialogActionListener;
    String selectedFreqency = "Weekly";
    String selectedName = "Hydro";
    TextView txtClose;

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
        spnName = view.findViewById(R.id.spnAddUtilityName);
        skbAmount = view.findViewById(R.id.skbAddUtility);
        txtSkbOutput = view.findViewById(R.id.txtAddUtilitySkbOutput);
        spnFrequency = view.findViewById(R.id.spnAddUtilitiesPaymentFrequency);
        btnAddUtility = view.findViewById(R.id.btnAddUtilityAddUtility);
        skbAmount.setOnSeekBarChangeListener(onSeekBarAmount);
        txtClose = view.findViewById(R.id.txtAddUtilityClose);
        txtClose.setOnTouchListener(onClose);
        skbAmount.setMax(MAX_UTILITY);
        skbAmount.setProgress(MAX_UTILITY/2);

        ArrayAdapter<CharSequence> frequencyAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.utilityFrequency, android.R.layout.simple_spinner_item);
        frequencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ArrayAdapter<CharSequence> nameAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.utilityName, android.R.layout.simple_spinner_item);
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnName.setOnItemSelectedListener(onNameSelected);
        spnName.setAdapter(nameAdapter);

        spnFrequency.setOnItemSelectedListener(onFrequencySelected);
        spnFrequency.setAdapter(frequencyAdapter);
        btnAddUtility.setOnClickListener(onAddUtility);


        return view;
    }

    View.OnTouchListener onClose = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            dismiss();
            return false;
        }
    };

    private Spinner.OnItemSelectedListener onNameSelected = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedName = parent.getItemAtPosition(position).toString();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    private Spinner.OnItemSelectedListener onFrequencySelected = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            selectedFreqency = item;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

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
                int utilityAmount = Integer.parseInt(txtSkbOutput.getText().toString());
                Utility utility = new Utility(selectedName, utilityAmount, selectedFreqency);
                dialogActionListener.setUtility(utility);
                getDialog().dismiss();
            }

        }
    };



    public void setDialogActionListener(UtilityDialogActionListener dialogActionListener) {
        this.dialogActionListener = dialogActionListener;
    }
}
