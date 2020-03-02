package com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

import io.opencensus.resource.Resource;

public class AddressEmptyStringValidator extends EmptyStringValidator {


    public AddressEmptyStringValidator(String s) {
        super(s);
        this.setResourceId(R.string.empty_address_error);
    }




}
