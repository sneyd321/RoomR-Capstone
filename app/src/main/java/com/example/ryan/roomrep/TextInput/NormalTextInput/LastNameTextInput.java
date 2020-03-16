package com.example.ryan.roomrep.TextInput.NormalTextInput;

import android.view.View;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TextInput.TextInput;

public class LastNameTextInput extends TextInput {
    public LastNameTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_last_name_empty_error);
        setTooShortValidator(1, R.string.last_name_too_short_error);
        setTooLongValidator(20, R.string.last_name_too_long_error);
    }
}
