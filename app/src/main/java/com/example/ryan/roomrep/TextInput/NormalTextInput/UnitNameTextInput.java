package com.example.ryan.roomrep.TextInput.NormalTextInput;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TextInput.TextInput;

public class UnitNameTextInput extends TextInput {
    public UnitNameTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_unit_name_error);
        setTooShortValidator(2, R.string.unit_name_too_short_error);
        setTooLongValidator(20, R.string.unit_name_too_long_error);
    }
}
