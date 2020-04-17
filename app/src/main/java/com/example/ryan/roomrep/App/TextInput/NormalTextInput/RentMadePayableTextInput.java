package com.example.ryan.roomrep.App.TextInput.NormalTextInput;

import android.view.View;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.TextInput.TextInput;

public class RentMadePayableTextInput extends TextInput {
    public RentMadePayableTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_rent_made_payable_error);
        setTooShortValidator(3, R.string.rent_made_payable_too_short_error);
        setTooLongValidator(20, R.string.rent_made_payable_too_long_error);
    }

    @Override
    public void invokeValidation() {
        if (getLayout().isEnabled()){
            super.invokeValidation();
            return;
        }
        getLayout().setError(null);
    }
}
