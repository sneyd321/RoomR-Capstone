package com.example.ryan.roomrep.App.TextInput.NormalTextInput;

import android.view.View;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.TextInput.TextInput;

public class PostalCodeTextInput extends TextInput {
    public PostalCodeTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_postal_code_error);
        setTooShortValidator(1, R.string.postal_code_too_short_error);
        setTooLongValidator(7, R.string.postal_code_too_long_error);
    }





}
