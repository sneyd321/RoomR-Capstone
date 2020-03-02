package com.example.ryan.roomrep.CompoundButtonInput;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class TenantHomeownerSwitchInput extends CompoundButtonInput {



    public TenantHomeownerSwitchInput(View view, int compoundButton) {
        super(view, compoundButton);
        this.compoundButton.setOnCheckedChangeListener(onSwapText);

    }

    private CompoundButton.OnCheckedChangeListener onSwapText = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            String text = b ? "Homeowner" : "Tenant";
            compoundButton.setText(text);
        }
    };



}
