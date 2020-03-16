package com.example.ryan.roomrep.TextInput.NormalTextInput;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TextInput.TextInput;

public class UnitNumberTextInput extends TextInput {
    public UnitNumberTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_unit_number_error);
        setTooShortValidator(1, R.string.unit_number_too_short_error);
        setTooLongValidator(8, R.string.unit_number_too_long_error);
    }

}
