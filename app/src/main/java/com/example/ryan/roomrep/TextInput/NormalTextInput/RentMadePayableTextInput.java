package com.example.ryan.roomrep.TextInput.NormalTextInput;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.ryan.roomrep.CompoundButtonInput.CompoundButtonInput;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TextInput.TextInput;

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
