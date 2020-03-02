package com.example.ryan.roomrep.TextInput.NumberTextInput;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class StorageAmountNumberTextInput extends NumberTextInput {
    public StorageAmountNumberTextInput(View view, int layoutId, int editTextId) {
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
            getLayout().setError(validationFacade.validateStorageAmount(getNumber()));
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
                getLayout().setError(validationFacade.validateStorageAmount(getNumber()));
                getEditText().setText(getText().equals("") ? "0" : Integer.toString(getNumber()));
                return;

            }
            getEditText().setText(getText().equals("0") ? "" : Integer.toString(getNumber()));
            getLayout().setErrorEnabled(false);
        }
    };
}
