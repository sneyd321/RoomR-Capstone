package com.example.ryan.roomrep.TextInput.AutoCompleteTextView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.ryan.roomrep.Classes.Validation.ValidationFacade;

public class AddressAutoCompleteTextInput extends AutoCompleteTextInput {


    public AddressAutoCompleteTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        editText.setOnFocusChangeListener(onFocusChangeListener);
        editText.addTextChangedListener(textWatcher);

    }

    @Override
    protected void setAdapter() {

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            getLayout().setError(validationFacade.validateAddress(charSequence.toString()));
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
                getLayout().setError(validationFacade.validateAddress(getText()));
                getEditText().setText(getText());
                return;

            }
            getLayout().setErrorEnabled(false);
        }
    };


}
