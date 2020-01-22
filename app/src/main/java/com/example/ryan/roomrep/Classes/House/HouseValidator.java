package com.example.ryan.roomrep.Classes.House;

import com.example.ryan.roomrep.Classes.Tenant.Validator;

import java.util.LinkedHashMap;
import java.util.Map;

public class HouseValidator implements Validator {

    @Override
    public <T> Map<Integer, String> validate(T object) {
        House house = (House) object;
        Map<Integer, String> validator = new LinkedHashMap<>();
        //validator.put(0, isAddressEmpty(house.getAddress()));
        //validate.put(1, isAddressValid(house.getAddress()));
        //validator.put(2, isRentGreaterThanZero(house.getRent()));
        validator.put(3, isSizeGreaterThanZero(house.getSize()));
        validator.put(4, isUnitTypeEmpty(house.getUnitType()));
        return validator;
    }


    private String isAddressEmpty(String address){
        if (address.length() == 0){
            return "Please enter an address.";
        }
        return "";
    }

    private String isAddressValid(String address) {
        if (address.matches( "\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)" ) && address.length() > 0){
            return "";
        }
        return "Please enter a valid address in the format '123 AddressName'.";
    }

    private String isSizeGreaterThanZero(int size) {
        if (size <= 0) {
            return "Please enter a size greater than 0.";
        }
        return "";
    }

    private String isRentGreaterThanZero(int rent) {
        if (rent <= 0) {
            return "Please enter a rent greater than 0.";
        }
        return "";
    }

    private String isUnitTypeEmpty(String unitType){
        if (unitType.length() == 0){
            return "Please enter a unit type.";
        }
        return "";
    }



}
