package com.example.ryan.roomrep.TextInput.NormalTextInput;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TextInput.TextInput;

public class PoBoxTextInput extends TextInput {
    public PoBoxTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_po_box_error);
        setTooShortValidator(1, R.string.po_box_too_short_error);
        setTooLongValidator(6, R.string.po_box_too_long_error);
    }



}
