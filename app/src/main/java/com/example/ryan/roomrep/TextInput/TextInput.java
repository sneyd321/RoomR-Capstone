package com.example.ryan.roomrep.TextInput;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.ryan.roomrep.Classes.Validation.InputSanitizer;
import com.example.ryan.roomrep.Classes.Validation.ValidationFacade;
import com.google.android.material.textfield.TextInputLayout;

public abstract class TextInput {

    protected View view;
    TextInputLayout layout;
    EditText editText;
    protected String errorText;
    protected ValidationFacade validationFacade;
    protected InputSanitizer inputSanitizer;


    public TextInput(View view, int layoutId, int editTextId) {
        this.view = view;
        this.layout = view.findViewById(layoutId);
        this.editText = view.findViewById(editTextId);
        this.layout.setEndIconOnClickListener(onClear);
        validationFacade = ValidationFacade.getInstance();
        inputSanitizer = InputSanitizer.getInstance();

    }

    public EditText getEditText() {
        return this.editText;
    }

    public String getText(){
        return inputSanitizer.sanitizeString(this.editText.getText().toString());
    }

    public TextInputLayout getLayout() {
        return this.layout;
    }

    public String getError() {
        return getLayout().getError() == null ? null : getLayout().getError().toString();
    }



    View.OnClickListener onClear = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            editText.setText("");
            if (!editText.hasFocus()) {
                layout.setError(errorText);
                return;
            }
            layout.setError(null);
        }
    };
}
