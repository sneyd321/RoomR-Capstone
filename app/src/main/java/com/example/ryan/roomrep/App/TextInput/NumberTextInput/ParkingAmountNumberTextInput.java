package com.example.ryan.roomrep.App.TextInput.NumberTextInput;

import android.view.View;

import com.example.ryan.roomrep.R;

public class ParkingAmountNumberTextInput extends NumberTextInput {
    public ParkingAmountNumberTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyIntValidator(R.string.parking_amount_empty_error);
        setTooLowValidator(0, R.string.parking_amount_too_short_error);
        setTooHighValidator(50, R.string.parking_amount_too_long_error);
    }
}
