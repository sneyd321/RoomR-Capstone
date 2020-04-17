package com.example.ryan.roomrep.App.TextInput.NormalTextInput;

import android.view.View;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.TextInput.TextInput;

public class EmailTextInput extends TextInput {
    public EmailTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_email_empty_error);
        setTooShortValidator(1, R.string.email_too_short_error);
        setTooLongValidator(25, R.string.email_too_long_error);
    }

    @Override
    public String getText() {
        return getEditText().getText().toString();
    }
}
