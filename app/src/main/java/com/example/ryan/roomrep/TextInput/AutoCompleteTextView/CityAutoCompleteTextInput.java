package com.example.ryan.roomrep.TextInput.AutoCompleteTextView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.ryan.roomrep.R;
import com.example.ryan.roomrep.TextInput.AutoCompleteTextView.AutoCompleteTextInput;

public class CityAutoCompleteTextInput extends AutoCompleteTextInput {

    public CityAutoCompleteTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setAdapter();
        editText.setOnFocusChangeListener(onFocusChangeListener);
        editText.addTextChangedListener(textWatcher);
    }

    @Override
    protected void setAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),android.R.layout.select_dialog_item, view.getContext().getResources().getStringArray(R.array.ON_cities));
        this.editText.setAdapter(adapter);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            getLayout().setError(validationFacade.validateCity(charSequence.toString()));
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
                getLayout().setError(validationFacade.validateCity(getText()));
                return;

            }
            getLayout().setErrorEnabled(false);
        }
    };


}
