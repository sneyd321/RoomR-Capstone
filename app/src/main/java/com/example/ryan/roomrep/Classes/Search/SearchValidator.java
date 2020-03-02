package com.example.ryan.roomrep.Classes.Search;

import com.example.ryan.roomrep.Classes.Tenant.Validator;

import java.util.LinkedHashMap;
import java.util.Map;

public class SearchValidator implements Validator<Search> {



    private String isProvinceSelected(String province) {
        if (province.isEmpty()) {
            return "Please select a province.";
        }
        return "";
    }

    private String isCitySelected(String city) {
        if (city.isEmpty()) {
            return "Please select a city.";
        }
        return "";
    }


    @Override
    public Map<Integer, String> validate(Search search) {
        Map<Integer, String> validator = new LinkedHashMap<>();
        validator.put(0, isProvinceSelected(search.getProvince()));
        validator.put(1, isCitySelected(search.getCity()));
        return validator;
    }
}
