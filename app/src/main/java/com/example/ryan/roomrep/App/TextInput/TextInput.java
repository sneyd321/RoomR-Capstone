package com.example.ryan.roomrep.App.TextInput;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.ryan.roomrep.Classes.Validation.InputSanitizer;
import com.example.ryan.roomrep.Classes.Validation.ValidationFacade;
import com.example.ryan.roomrep.R;
import com.google.android.material.textfield.TextInputLayout;

public abstract class TextInput {

    private View view;
    private TextInputLayout layout;
    private EditText editText;
    private ValidationFacade validationFacade;
    private InputSanitizer inputSanitizer;

    public TextInput(View view, int layoutId, int editTextId) {
        this.view = view;
        this.layout = view.findViewById(layoutId);
        this.editText = view.findViewById(editTextId);
        this.layout.setEndIconOnClickListener(onClear);
        this.editText.addTextChangedListener(textWatcher);
        this.editText.setOnFocusChangeListener(onFocusChangeListener);
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

    public void invokeValidation() {
        getLayout().setError(validationFacade.validate(getText()));
    }

    EditText.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            //If edittext does not have focus
            if (!b) {
                getLayout().setErrorEnabled(true);
                getLayout().setError(validationFacade.validate(getText()));
                return;
            }
            getLayout().setErrorEnabled(false);
        }
    };


    protected TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            getLayout().setError(validationFacade.validate(charSequence.toString()));
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    private View.OnClickListener onClear = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            editText.setText("");
            if (!editText.hasFocus()) {
                layout.setError(validationFacade.validate(getText()));
                return;
            }
            layout.setError(null);
        }
    };

    protected ValidationFacade getValidationFacade() {
        return validationFacade;
    }

    protected InputSanitizer getInputSanitizer() {
        return inputSanitizer;
    }

    protected void setTooShortValidator(int minThreshold, int resourceId) {
        getValidationFacade().addTooShortValidator(minThreshold,
                this.view.getContext().getString(resourceId) + " " + minThreshold + " " + this.view.getContext().getString(R.string.too_string_error_postfix));
    }

    protected void setTooLongValidator(int maxThreshold, int resourceId) {
        getValidationFacade().addTooLongValidator(maxThreshold,
                this.view.getContext().getString(resourceId) + " " + maxThreshold + " " + this.view.getContext().getString(R.string.too_string_error_postfix));
    }

    protected void setEmptyValidator(int resourceId) {
        getValidationFacade().addEmptyValidator(this.view.getContext().getString(resourceId));
    }

    public View getView() {
        return view;
    }

    public void setEnabled(boolean enabled) {
        getEditText().setEnabled(enabled);
        getLayout().setEnabled(enabled);
    }
}
