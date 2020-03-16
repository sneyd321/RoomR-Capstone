package com.example.ryan.roomrep.Factories;

import android.view.View;

import com.example.ryan.roomrep.CompoundButtonInput.AnimatedCompoundButtonInput;
import com.example.ryan.roomrep.CompoundButtonInput.AnimatedSelectedNotSelectedSwitchInput;
import com.example.ryan.roomrep.CompoundButtonInput.CompoundButtonInput;
import com.example.ryan.roomrep.CompoundButtonInput.NormalCheckboxInput;
import com.example.ryan.roomrep.CompoundButtonInput.NormalSwitchInput;
import com.example.ryan.roomrep.CompoundButtonInput.RadioButtonCompoundButtonInput;
import com.example.ryan.roomrep.CompoundButtonInput.SelectedNotSelectedSwitchInput;
import com.example.ryan.roomrep.CompoundButtonInput.TenantHomeownerSwitchInput;
import com.example.ryan.roomrep.R;


public class CompoundButtonFactory extends AbstractFactory {


    protected CompoundButtonFactory(View view) {
        super(view);
    }

    public SelectedNotSelectedSwitchInput getSelectedNotSelectedSwitchInput(int compoundButtonResourceId) {
        switch (compoundButtonResourceId) {
            case R.id.swtAddHouseIsCondo:
                return new SelectedNotSelectedSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseAirConditioning:
                return new SelectedNotSelectedSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseGuestParking:
                return new SelectedNotSelectedSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseSmoking:
                return new SelectedNotSelectedSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseLaundry:
                return new SelectedNotSelectedSwitchInput(getView(),compoundButtonResourceId);
            case R.id.swtAddHouseTenantInsurance:
                return new SelectedNotSelectedSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseAdditionalTerm1:
                return new SelectedNotSelectedSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseAdditionalTerm2:
                return new SelectedNotSelectedSwitchInput(getView(), compoundButtonResourceId);
            default:
                return null;

        }


    }

    public RadioButtonCompoundButtonInput getRadioButtonCompoundButtonInput(int compoundButtonResourceId) {
        switch (compoundButtonResourceId) {
            case R.id.rdgAddHouseRentDueDate:
                return new RadioButtonCompoundButtonInput(getView(), 0, compoundButtonResourceId);
            case R.id.rdgLogin:
                return new RadioButtonCompoundButtonInput(getView(), 0, compoundButtonResourceId);
            default:
                return null;
        }
    }

    public AnimatedCompoundButtonInput getAnimatedCompoundButtonInput(int compoundButtonResourceId) {
        switch (compoundButtonResourceId) {
            case R.id.chkAddHouseRentPaymentMethod2:
                return new AnimatedCompoundButtonInput(getView(), compoundButtonResourceId, R.id.tilAddHouseRentMadePayable, R.id.edtAddHouseRentMadePayable);
            default:
                return null;
        }

    }

    public AnimatedSelectedNotSelectedSwitchInput getAnimatedSelectedNotSelectedSwitchInput(int compoundButtonResourceId) {
        switch (compoundButtonResourceId) {
            case R.id.swtAddHouseParking:
                return new AnimatedSelectedNotSelectedSwitchInput(getView(), compoundButtonResourceId, R.id.tilAddHouseParkingAmount, R.id.edtAddHouseParkingAmount);
            case R.id.swtAddHouseStorage:
                return new AnimatedSelectedNotSelectedSwitchInput(getView(), compoundButtonResourceId, R.id.tilAddHouseStorageAmount, R.id.edtAddHouseStorageAmount);
            default:
                return null;
        }
    }

    public NormalCheckboxInput getNormalCheckboxInput(int compoundButtonResourceId) {
        switch (compoundButtonResourceId) {
            case R.id.chkAddHouseRentPaymentMethod1:
                return new NormalCheckboxInput(getView(), compoundButtonResourceId);
            case R.id.chkAddHouseRentPaymentMethod3:
                return new NormalCheckboxInput(getView(), compoundButtonResourceId);
            default:
                return null;
        }
    }

    public TenantHomeownerSwitchInput getTenantHomeownerSwitchInput(int compoundButtonResourceId) {
         switch (compoundButtonResourceId) {
             case R.id.swtAddHouseWaterUtility:
                 return new TenantHomeownerSwitchInput(getView(), compoundButtonResourceId);
             case R.id.swtAddHouseHeatUtility:
                 return new TenantHomeownerSwitchInput(getView(), compoundButtonResourceId);
             case R.id.swtAddHouseElectricityUtility:
                 return new TenantHomeownerSwitchInput(getView(), compoundButtonResourceId);
             default:
                 return null;
         }
    }


}
