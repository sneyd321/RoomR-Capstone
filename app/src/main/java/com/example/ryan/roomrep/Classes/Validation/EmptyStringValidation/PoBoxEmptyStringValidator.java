package com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class PoBoxEmptyStringValidator extends EmptyStringValidator {

    public PoBoxEmptyStringValidator(String s) {
        super(s);
        this.setResourceId(R.string.empty_po_box_error);
    }


}
