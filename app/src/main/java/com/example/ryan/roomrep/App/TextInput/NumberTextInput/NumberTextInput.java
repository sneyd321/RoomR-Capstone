package com.example.ryan.roomrep.App.TextInput.NumberTextInput;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.App.TextInput.TextInput;

public abstract  class NumberTextInput extends TextInput {



    public NumberTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        getLayout().setEndIconOnClickListener(this.onClear);
        getEditText().setOnFocusChangeListener(this.onFocusChangeListener);
        getEditText().removeTextChangedListener(super.textWatcher);
        getEditText().addTextChangedListener(this.textWatcher);
    }

    public int getNumber() {
        return getInputSanitizer().sanitizeInt(getText());
    }

    View.OnClickListener onClear = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!getEditText().hasFocus()) {
                getEditText().setText("0");
                getLayout().setError(getValidationFacade().validate(getNumber()));
                return;
            }
            getEditText().setText("");
            getLayout().setError(null);
        }
    };

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            getLayout().setError(getValidationFacade().validate(getNumber()));

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void invokeValidation() {
        getLayout().setError(getValidationFacade().validate(getNumber()));
    }

    EditText.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            //If edittext does not have focus
            if (!b) {
                getLayout().setErrorEnabled(true);
                getLayout().setError(getValidationFacade().validate(getNumber()));
                getEditText().setText(Integer.toString(getNumber()).isEmpty() ? "0" : Integer.toString(getNumber()));
                return;

            }
            getEditText().setText(Integer.toString(getNumber()).equals("0") ? "" : Integer.toString(getNumber()));
            getLayout().setErrorEnabled(false);


        }
    };

    protected void setEmptyIntValidator(int resourceId) {
        getValidationFacade().addIntEmptyValidator(getView().getContext().getString(resourceId));
    }

    protected void setTooHighValidator(int maxThreshold, int resourceId) {
        getValidationFacade().addTooHighValidator(maxThreshold,
                getView().getContext().getString(resourceId) + " " + maxThreshold + " " + getView().getContext().getString(R.string.too_number_error_postfix));
    }

    protected void setTooLowValidator(int minThreshold, int resourceId) {
        getValidationFacade().addTooLowValidator(minThreshold,
                getView().getContext().getString(resourceId) + " " + minThreshold + " " + getView().getContext().getString(R.string.too_number_error_postfix));
    }

    protected void setZeroValidator(int resourceId) {
        getValidationFacade().addZeroValidator(getView().getContext().getString(resourceId));
    }
}
