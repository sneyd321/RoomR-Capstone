package com.example.ryan.roomrep.App.TextInput.NormalTextInput;

import android.view.View;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.TextInput.TextInput;

public class PoBoxTextInput extends TextInput {
    public PoBoxTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_po_box_error);
        setTooShortValidator(1, R.string.po_box_too_short_error);
        setTooLongValidator(6, R.string.po_box_too_long_error);
    }



}
