package com.example.ryan.roomrep.TextInput.AutoCompleteTextView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.ryan.roomrep.R;

public class CityAutoCompleteTextInput extends AutoCompleteTextInput {

    public CityAutoCompleteTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setAdapter();
        setEmptyValidator(R.string.empty_city_error);
        setTooShortValidator(1, R.string.city_too_short_error);
        setTooLongValidator(15, R.string.city_too_long_error);
    }

    @Override
    protected void setAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getView().getContext(),android.R.layout.select_dialog_item, getView().getContext().getResources().getStringArray(R.array.ON_cities));
        this.editText.setAdapter(adapter);
    }



}
