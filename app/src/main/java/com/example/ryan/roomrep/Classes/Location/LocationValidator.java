package com.example.ryan.roomrep.Classes.Location;

import com.example.ryan.roomrep.Classes.Tenant.Validator;

import java.util.LinkedHashMap;
import java.util.Map;

public class LocationValidator implements Validator<Location> {

    @Override
    public Map<Integer, String> validate(Location location) {
        Map<Integer, String> validator = new LinkedHashMap<>();
        validator.put(0, isAddressEmpty(location.getAddress()));
        validator.put(1, isProvinceEmpty(location.getAddress()));
        validator.put(2, isCityEmpty(location.getAddress()));
        validator.put(3, isPostalCodeEmpty(location.getAddress()));
        return validator;
    }

    private String isAddressEmpty(String address) {
        if (address.isEmpty()) {
            return "Please enter an address.";
        }
        return "";
    }

    private String isProvinceEmpty(String address) {
        if (address.isEmpty()) {
            return "Please enter a province.";
        }
        return "";
    }

    private String isCityEmpty(String address) {
        if (address.isEmpty()) {
            return "Please enter a city.";
        }
        return "";
    }

    private String isPostalCodeEmpty(String address) {
        if (address.isEmpty()) {
            return "Please enter a postal code";
        }
        return "";
    }


}
