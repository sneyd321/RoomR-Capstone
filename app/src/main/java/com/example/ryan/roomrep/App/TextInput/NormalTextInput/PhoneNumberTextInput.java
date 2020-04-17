package com.example.ryan.roomrep.App.TextInput.NormalTextInput;

import android.view.View;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.TextInput.TextInput;

public class PhoneNumberTextInput extends TextInput {
    public PhoneNumberTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_phone_number_empty_error);
        setTooShortValidator(1, R.string.phone_number_too_short_error);
        setTooLongValidator(11, R.string.phone_number_too_long_error);
    }
}
