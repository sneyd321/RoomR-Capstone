package com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.Classes.Validation.Validator;

public abstract class EmptyStringValidator extends Validator<String> {


    public EmptyStringValidator(String s) {
        super(s);
    }

    @Override
    public boolean validate() {
        return !this.value.isEmpty();
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }


    public String getErrorMessage() {
        return App.getContext().getResources().getString(resourceId);
    }
}
