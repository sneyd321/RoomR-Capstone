package com.example.ryan.roomrep.App.Factories;

import android.view.View;

import com.example.ryan.roomrep.App.CompoundButtonInput.AnimatedCompoundButtonInput;
import com.example.ryan.roomrep.App.CompoundButtonInput.AnimatedSelectedNotSelectedSwitchInput;
import com.example.ryan.roomrep.App.CompoundButtonInput.NormalCheckboxInput;
import com.example.ryan.roomrep.App.CompoundButtonInput.RadioButtonCompoundButtonInput;
import com.example.ryan.roomrep.App.CompoundButtonInput.SelectedNotSelectedSwitchInput;
import com.example.ryan.roomrep.App.CompoundButtonInput.TenantHomeownerSwitchInput;
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
            case R.id.swtAddHouseLaundry:
                return new SelectedNotSelectedSwitchInput(getView(),compoundButtonResourceId);
            case R.id.swtAddHouseGas:
                return new SelectedNotSelectedSwitchInput(getView(),compoundButtonResourceId);
            case R.id.swtAddHouseStorage:
                return new SelectedNotSelectedSwitchInput(getView(),compoundButtonResourceId);
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
