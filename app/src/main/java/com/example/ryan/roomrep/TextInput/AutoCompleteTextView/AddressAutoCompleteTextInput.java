package com.example.ryan.roomrep.TextInput.AutoCompleteTextView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.ryan.roomrep.R;

public class AddressAutoCompleteTextInput extends AutoCompleteTextInput {


    public AddressAutoCompleteTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setEmptyValidator(R.string.empty_address_error);
        setTooShortValidator(3, R.string.address_too_short_error);
        setTooLongValidator(20, R.string.address_too_long_error);

    }

    @Override
    protected void setAdapter() {

    }




}
