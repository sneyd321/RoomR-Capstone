package com.example.ryan.roomrep.TextInput.NormalTextInput;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TextInput.TextInput;

public class PostalCodeTextInput extends TextInput {
    public PostalCodeTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_postal_code_error);
        setTooShortValidator(1, R.string.postal_code_too_short_error);
        setTooLongValidator(7, R.string.postal_code_too_long_error);
    }





}
