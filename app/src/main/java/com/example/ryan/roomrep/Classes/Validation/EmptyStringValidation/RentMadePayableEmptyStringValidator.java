package com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class RentMadePayableEmptyStringValidator extends EmptyStringValidator {

    public RentMadePayableEmptyStringValidator(String s) {
        super(s);
        this.setResourceId(R.string.empty_rent_made_payable_error);
    }


}
