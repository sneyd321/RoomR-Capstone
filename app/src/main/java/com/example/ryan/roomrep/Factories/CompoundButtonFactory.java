package com.example.ryan.roomrep.Factories;

import android.view.View;

import com.example.ryan.roomrep.CompoundButtonInput.AnimatedCompoundButtonInput;
import com.example.ryan.roomrep.CompoundButtonInput.CompoundButtonInput;
import com.example.ryan.roomrep.CompoundButtonInput.NormalCheckboxInput;
import com.example.ryan.roomrep.CompoundButtonInput.NormalSwitchInput;
import com.example.ryan.roomrep.CompoundButtonInput.RadioButtonCompoundButtonInput;
import com.example.ryan.roomrep.CompoundButtonInput.TenantHomeownerSwitchInput;
import com.example.ryan.roomrep.R;


public class CompoundButtonFactory extends AbstractFactory {


    protected CompoundButtonFactory(View view) {
        super(view);
    }

    public CompoundButtonInput getCompoundButtonInput(int compoundButtonResourceId) {
        switch (compoundButtonResourceId) {
            case R.id.swtAddHouseIsCondo:
                return new NormalSwitchInput(getView(), compoundButtonResourceId);
            case R.id.rdgAddHouseRentDueDate:
                return new RadioButtonCompoundButtonInput(getView(), 0, compoundButtonResourceId);
            case R.id.chkAddHouseRentPaymentMethod1:
                return new NormalCheckboxInput(getView(), compoundButtonResourceId);
            case R.id.chkAddHouseRentPaymentMethod2:
                return new AnimatedCompoundButtonInput(getView(), compoundButtonResourceId, R.id.tilAddHouseRentMadePayable);
            case R.id.chkAddHouseRentPaymentMethod3:
                return new NormalCheckboxInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseAirConditioning:
                return new NormalSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseParking:
                return new AnimatedCompoundButtonInput(getView(), compoundButtonResourceId, R.id.tilAddHouseParkingAmount);
            case R.id.swtAddHouseGuestParking:
                return new NormalSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseStorage:
                return new AnimatedCompoundButtonInput(getView(), compoundButtonResourceId, R.id.tilAddHouseStorageAmount);
            case R.id.swtAddHouseSmoking:
                return new NormalSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseLaundry:
                return new NormalSwitchInput(getView(),compoundButtonResourceId);
            case R.id.swtAddHouseTenantInsurance:
                return new NormalSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseWaterUtility:
                return new TenantHomeownerSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseHeatUtility:
                return new TenantHomeownerSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseElectricityUtility:
                return new TenantHomeownerSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddHouseAdditionalTerm1:
                return new NormalSwitchInput(getView(), compoundButtonResourceId);
            case R.id.swtAddAgreementAdditionalTerm2:
                return new NormalSwitchInput(getView(), compoundButtonResourceId);
            default:
                return null;

        }
    }


}
