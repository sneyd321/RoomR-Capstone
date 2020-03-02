package com.example.ryan.roomrep.TextInput.NumberTextInput;

import android.view.View;

import com.example.ryan.roomrep.Classes.Validation.InputSanitizer;
import com.example.ryan.roomrep.TextInput.TextInput;

public abstract  class NumberTextInput extends TextInput {
    public NumberTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        getLayout().setEndIconOnClickListener(onClear);

    }

    public int getNumber() {

        return inputSanitizer.sanitizeInt(getEditText().getText().toString());
    }

    View.OnClickListener onClear = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (!getEditText().hasFocus()) {
                getEditText().setText("0");
                getLayout().setError(errorText);
                return;
            }
            getEditText().setText("");
            getLayout().setError(null);
        }
    };
}
