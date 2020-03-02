package com.example.ryan.roomrep.Classes.Validation.IntTooLowValidation;

import android.content.res.Resources;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.R;

public class StorageAmountTooLowValidator extends IntTooLowValidator {

    private final int MIN_VALUE = -1;

    public StorageAmountTooLowValidator(Integer integer) {
        super(integer);
        setThreshold(MIN_VALUE);
        setResourceId(R.string.storage_amount_too_short_error);
    }


}
