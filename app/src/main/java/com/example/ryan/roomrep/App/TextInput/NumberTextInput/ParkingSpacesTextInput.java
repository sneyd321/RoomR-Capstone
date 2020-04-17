package com.example.ryan.roomrep.App.TextInput.NumberTextInput;

import android.view.View;

import com.example.ryan.roomrep.R;

public class ParkingSpacesTextInput extends NumberTextInput {
    public ParkingSpacesTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyIntValidator(R.string.empty_parking_space_error);
        setTooLowValidator(0, R.string.parking_space_too_short_error);
        setTooHighValidator(10, R.string.parking_space_too_long_error);
    }




}
