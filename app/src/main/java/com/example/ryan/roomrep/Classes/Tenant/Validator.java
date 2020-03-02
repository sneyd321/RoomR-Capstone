package com.example.ryan.roomrep.Classes.Tenant;

import java.util.Map;

public interface Validator<T> {

    Map<Integer, String> validate(T object);

}
