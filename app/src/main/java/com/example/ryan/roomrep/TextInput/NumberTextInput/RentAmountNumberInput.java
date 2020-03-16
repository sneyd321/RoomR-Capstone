package com.example.ryan.roomrep.TextInput.NumberTextInput;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.ryan.roomrep.R;

public class RentAmountNumberInput extends NumberTextInput {

    public RentAmountNumberInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyIntValidator(R.string.empty_rent_amount_error);
        setZeroValidator(R.string.rent_amount_zero_empty);
        setTooLowValidator(1, R.string.rent_amount_too_short_error);
        setTooHighValidator(2000, R.string.rent_amount_too_long_error);
    }


}
