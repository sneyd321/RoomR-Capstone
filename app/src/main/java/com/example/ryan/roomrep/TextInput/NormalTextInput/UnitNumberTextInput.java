package com.example.ryan.roomrep.TextInput.NormalTextInput;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.ryan.roomrep.TextInput.TextInput;

public class UnitNumberTextInput extends TextInput {
    public UnitNumberTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        getEditText().setOnFocusChangeListener(onFocusChangeListener);
        getEditText().addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            getLayout().setError(validationFacade.validateUnitNumber(charSequence.toString()));
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    EditText.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            //If edittext does not have focus
            if (!b) {
                getLayout().setErrorEnabled(true);
                getLayout().setError(validationFacade.validateUnitNumber(getText()));
                return;

            }
            getLayout().setErrorEnabled(false);
        }
    };
}
