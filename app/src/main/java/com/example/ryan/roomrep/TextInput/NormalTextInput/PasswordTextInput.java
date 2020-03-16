package com.example.ryan.roomrep.TextInput.NormalTextInput;

import android.view.View;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TextInput.TextInput;

public class PasswordTextInput extends TextInput {
    public PasswordTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_password_empty_error);
        setTooShortValidator(6, R.string.password_short_error);
        setTooLongValidator(15, R.string.password_too_long_error);
    }

    @Override
    public String getText() {
        return getEditText().getText().toString();
    }
}
