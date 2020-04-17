package com.example.ryan.roomrep.App.TextInput.NormalTextInput;

import android.view.View;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.TextInput.TextInput;

public class UnitNumberTextInput extends TextInput {
    public UnitNumberTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_unit_number_error);
        setTooShortValidator(1, R.string.unit_number_too_short_error);
        setTooLongValidator(8, R.string.unit_number_too_long_error);
    }

}
