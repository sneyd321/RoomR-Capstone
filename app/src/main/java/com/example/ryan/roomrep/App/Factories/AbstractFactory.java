package com.example.ryan.roomrep.App.Factories;

import android.view.View;

public class AbstractFactory {


    private View view;

    protected AbstractFactory(View view) {
        this.view = view;
    }

    protected View getView() {
        return this.view;
    }



    public static AbstractFactory getFactory(View view, FactoryType factoryType) {
        switch (factoryType) {
            case TextInput:
                return new TextInputFactory(view);
            case CompoundButtonInput:
                return new CompoundButtonFactory(view);
            case SelectDateDialog:
                return new SelectDateFactory(view);
            default:
                return null;
        }
    }
}
