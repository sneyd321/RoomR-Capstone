package com.example.ryan.roomrep.Classes.Validation;

import com.example.ryan.roomrep.Classes.App;

public abstract class Validator<T> {


    protected T value;

    protected int resourceId;


    public Validator(T t) {
        this.value = t;
    }

    public abstract boolean validate();

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public abstract String getErrorMessage();




}
