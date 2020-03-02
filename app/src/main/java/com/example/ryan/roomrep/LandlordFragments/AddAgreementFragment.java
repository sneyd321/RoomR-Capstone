package com.example.ryan.roomrep.LandlordFragments;

import android.animation.Animator;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.ryan.roomrep.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddAgreementFragment extends Fragment {


    private Spinner spnCity;
    private Spinner spnProvince;
    private EditText edtPostalCode;
    private EditText edtUnitName;
    private ImageButton btnAddLocation;
    private Spinner spnBedNumber;
    private Spinner spnBathNumber;
    private EditText edtSize;
    private EditText edtStartDate;
    private EditText edtEndDate;
    private Spinner spnRentDueDate;
    private EditText edtRentPayableTo;
    private CheckBox paymentMethod1;
    private CheckBox paymentMethod2;
    private CheckBox paymentMethod3;
    private SeekBar skbRent;
    private TextView skbOutput;
    private Button btnAddUtility;
    private RecyclerView rcyUtilities;

    private Switch swtKeyDeposit;
    private Switch swtAirConditioning;
    private Switch swtLaundry;
    private Switch swtStorage;
    private  Switch swtGuestParking;
    private Switch swtFurnished;
    private Switch swtOutdoorSpace;
    private Switch swtSmoking;
    private Switch swtTenantInsurance;
    private Switch swtAppliance;
    private Switch swtAdditionalTerm1;
    private Switch swtAdditionalTerm2;


    private LinearLayout llKeyDepositDrawer;
    private SeekBar skbKeyDeposit;
    private TextView txtKeyDepositOutput;
    private LinearLayout llLaundryDrawer;
    private LinearLayout llStorageDrawer;
    private SeekBar skbStorage;
    private TextView txtStorageOutput;
    private LinearLayout llParkingDrawer;
    private SeekBar skbParking;
    private  TextView txtParkingOutput;
    private LinearLayout llOutdoorDrawer;
    private LinearLayout llApplianceDrawer;
    private CheckBox chkAppliance1;
    private CheckBox chkAppliance2;
    private CheckBox chkAppliance3;
    private CheckBox chkAppliance4;
    private CheckBox chkAppliance5;
    private CheckBox chkAppliance6;

    final Calendar startDate = Calendar.getInstance();
    final Calendar endDate = Calendar.getInstance();



    private void initUI(View view) {
        //rbtnTenancyType = view.findViewById(R.id.rbtnFixed);
        edtStartDate = view.findViewById(R.id.edtAddAgreementStartDate);
        edtStartDate.setOnClickListener(onStartDate);
        edtEndDate = view.findViewById(R.id.edtAddAgreementEndDate);
        edtEndDate.setOnClickListener(onEndDate);

        edtRentPayableTo = view.findViewById(R.id.edtAddAgreementMadePayable);
        paymentMethod1 = view.findViewById(R.id.chkAddAgreementPaymentMethod1);
        paymentMethod2 = view.findViewById(R.id.chkAddAgreementPaymentMethod2);
        paymentMethod3 = view.findViewById(R.id.chkAddAgreementPaymentMethod3);
        //paymentMethod4 = view.findViewById(R.id.chkAddAgreementPaymentMethod4);
        swtAirConditioning = view.findViewById(R.id.swtAddAgreementAirConditioning);
        swtFurnished = view.findViewById(R.id.swtAddAgreementFurnished);
        swtSmoking = view.findViewById(R.id.swtAddAgreementSmoking);
        swtTenantInsurance = view.findViewById(R.id.swtAddAgreementTenantInsurance);

        swtAdditionalTerm1 = view.findViewById(R.id.swtAddAgreementAdditionalTerm1);
        swtAdditionalTerm2 = view.findViewById(R.id.swtAddAgreementAdditionalTerm2);
        initSpinner(view);
        initKeyDepositDrawer(view);
        initLaundryDrawer(view);
        initStorageDrawer(view);
        initParkingDrawer(view);
        initOutdoorDrawer(view);
        initApplianceDrawer(view);

    }


    private void initApplianceDrawer(View view) {
        swtAppliance = view.findViewById(R.id.swtAddAgreementAppliances);
        llApplianceDrawer = view.findViewById(R.id.llAddAgreementApplianceDrawer);
        chkAppliance1 = view.findViewById(R.id.chkAddAgreementAppliance1);
        chkAppliance1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String name = buttonView.getText().toString();

            }
        });
        chkAppliance2 = view.findViewById(R.id.chkAddAgreementAppliance2);
        chkAppliance3 = view.findViewById(R.id.chkAddAgreementAppliance3);
        chkAppliance4 = view.findViewById(R.id.chkAddAgreementAppliance4);
        chkAppliance5 = view.findViewById(R.id.chkAddAgreementAppliance5);
        chkAppliance6 = view.findViewById(R.id.chkAddAgreementAppliance6);
        swtAppliance.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                transitionAnimation(isChecked, llApplianceDrawer);
            }
        });
    }

    private void initOutdoorDrawer(View view){
        swtOutdoorSpace = view.findViewById(R.id.swtAddAgreementAvailableOutdoorSpace);
        llOutdoorDrawer = view.findViewById(R.id.llAddAgreementOutdoorDrawer);
        swtOutdoorSpace.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                transitionAnimation(isChecked, llOutdoorDrawer);
            }
        });
    }

    private void initParkingDrawer(View view) {
        swtGuestParking = view.findViewById(R.id.swtAddAgreementGuestParking);
        llParkingDrawer = view.findViewById(R.id.llAddAgreementParkingDrawer);
        skbParking = view.findViewById(R.id.skbAddAgreementParking);
        txtParkingOutput = view.findViewById(R.id.txtAddAgreementSkbParkingOutput);
        skbParking.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtParkingOutput.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        swtGuestParking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                transitionAnimation(isChecked, llParkingDrawer);
            }
        });
    }

    private void initKeyDepositDrawer(View view){
        swtKeyDeposit = view.findViewById(R.id.swtAddAgreementKeyDeposit);
        llKeyDepositDrawer = view.findViewById(R.id.llAddAgreementKeyDepositDrawer);
        skbKeyDeposit = view.findViewById(R.id.skbAddAgreementKeyDeposit);
        txtKeyDepositOutput = view.findViewById(R.id.txtAddAgreementSkbKeyDepositOutput);
        skbKeyDeposit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtKeyDepositOutput.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        swtKeyDeposit.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                transitionAnimation(isChecked, llKeyDepositDrawer);
            }
        });
    }

    private void initLaundryDrawer(View view) {
        llLaundryDrawer = view.findViewById(R.id.llAddAgreementLaundryDrawer);
        swtLaundry = view.findViewById(R.id.swtAddAgreementLaundry);
        swtLaundry.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                transitionAnimation(isChecked, llLaundryDrawer);
            }
        });
    }

    private void initStorageDrawer(View view) {
        swtStorage = view.findViewById(R.id.swtAddAgreementStorage);

        llStorageDrawer = view.findViewById(R.id.llAddAgreementStorageDrawer);
        skbStorage = view.findViewById(R.id.skbAddAgreementStorage);
        txtStorageOutput = view.findViewById(R.id.txtAddAgreementSkbStorageOutput);
        skbStorage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtStorageOutput.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        swtStorage.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                transitionAnimation(isChecked, llStorageDrawer);
            }
        });
    }

    private void initSpinner(View view) {
        spnRentDueDate = view.findViewById(R.id.spnRentDueDate);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.rentDueDate, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRentDueDate.setAdapter(adapter);
        spnRentDueDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_agreement, container, false);
        initUI(view);



        return view;
    }





    private void transitionAnimation(boolean isChecked, final LinearLayout layoutDrawer){
        if (isChecked){
            layoutDrawer.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(layoutDrawer);
        }
        else {
            YoYo.with(Techniques.SlideOutLeft).onEnd(new YoYo.AnimatorCallback() {
                @Override
                public void call(Animator animator) {
                    layoutDrawer.setVisibility(View.GONE);
                }
            }).duration(1000).playOn(layoutDrawer);

        }
    }

    EditText.OnClickListener onStartDate = new EditText.OnClickListener() {
        @Override
        public void onClick(View v) {
            new DatePickerDialog(getActivity(), onStartDateListener, startDate
                    .get(Calendar.YEAR), startDate.get(Calendar.MONTH),
                    startDate.get(Calendar.DAY_OF_MONTH)).show();

        }
    };

    EditText.OnClickListener onEndDate = new EditText.OnClickListener() {
        @Override
        public void onClick(View v) {
            new DatePickerDialog(getActivity(), onEndDateListener, endDate
                    .get(Calendar.YEAR), endDate.get(Calendar.MONTH),
                    endDate.get(Calendar.DAY_OF_MONTH)).show();

        }
    };

    DatePickerDialog.OnDateSetListener onStartDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            startDate.set(Calendar.YEAR, year);
            startDate.set(Calendar.MONTH, month);
            startDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setStartDate();
        }
    };
    DatePickerDialog.OnDateSetListener onEndDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            endDate.set(Calendar.YEAR, year);
            endDate.set(Calendar.MONTH, month);
            endDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setEndDate();
        }
    };

    private void setStartDate() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);

        edtStartDate.setText(sdf.format(startDate.getTime()));
    }
    private void setEndDate() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);

        edtEndDate.setText(sdf.format(endDate.getTime()));
    }






}
