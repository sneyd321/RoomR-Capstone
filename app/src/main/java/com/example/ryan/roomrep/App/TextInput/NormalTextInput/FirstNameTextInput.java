package com.example.ryan.roomrep.App.TextInput.NormalTextInput;

import android.view.View;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.TextInput.TextInput;

public class FirstNameTextInput extends TextInput {
    public FirstNameTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_first_name_error);
        setTooShortValidator(1, R.string.first_name_too_short_error);
        setTooLongValidator(20, R.string.first_name_too_long_error);
    }
}
