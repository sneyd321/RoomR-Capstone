package com.example.ryan.roomrep.Classes.House;

import com.example.ryan.roomrep.Classes.Tenant.Validator;

import java.util.LinkedHashMap;
import java.util.Map;

public class HouseValidator implements Validator {

    @Override
    public <T> Map<Integer, String> validator(T object) {
        House house = (House) object;
        Map<Integer, String> validator = new LinkedHashMap<>();
        validator.put(0, isAddressEmpty(house.getAddress()));
        //validator.put(1, isAddressValid(house.getAddress()));
        validator.put(2, isRentGreaterThanZero(house.getRent()));
        validator.put(3, isSizeGreaterThanZero(house.getSize()));
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



}
