package com.example.ryan.roomrep.Classes.Validation;

import com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation.AddressEmptyStringValidator;
import com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation.CityEmptyStringValidator;
import com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation.PoBoxEmptyStringValidator;
import com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation.PostalCodeEmptyStringValidator;
import com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation.RentMadePayableEmptyStringValidator;
import com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation.UnitNameEmptyStringValidator;
import com.example.ryan.roomrep.Classes.Validation.EmptyStringValidation.UnitNumberEmptyStringValidator;
import com.example.ryan.roomrep.Classes.Validation.IntIsZeroValidation.RentAmountIsZeroValidator;
import com.example.ryan.roomrep.Classes.Validation.IntTooHighValidation.ParkingAmountTooHighValidator;
import com.example.ryan.roomrep.Classes.Validation.IntTooHighValidation.ParkingSpacesTooHighValidator;
import com.example.ryan.roomrep.Classes.Validation.IntTooHighValidation.RentAmountTooHighValidator;
import com.example.ryan.roomrep.Classes.Validation.IntTooHighValidation.StorageAmountTooHighValidator;
import com.example.ryan.roomrep.Classes.Validation.IntTooLowValidation.ParkingAmountTooLowValidator;
import com.example.ryan.roomrep.Classes.Validation.IntTooLowValidation.ParkingSpaceTooLowValidator;
import com.example.ryan.roomrep.Classes.Validation.IntTooLowValidation.RentAmountTooLowValidator;
import com.example.ryan.roomrep.Classes.Validation.IntTooLowValidation.StorageAmountTooLowValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation.AddressTooLongValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation.CityTooLongValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation.PoBoxTooLongValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation.PostalCodeTooLongValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation.RentMadePayableTooLongValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation.UnitNameTooLongValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooLongValidation.UnitNumberTooLongValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation.AddressTooShortValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation.CityTooShortValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation.PoBoxTooShortValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation.PostalCodeTooShortValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation.RentMadePayableTooShortValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation.UnitNameTooShortValidator;
import com.example.ryan.roomrep.Classes.Validation.StringTooShortValidation.UnitNumberTooShortValidator;

import java.util.ArrayList;
import java.util.List;

public class ValidationFacade {


    private static ValidationFacade instance = null;


    public static ValidationFacade getInstance() {
        if (instance == null) {
            return new ValidationFacade();
        }
        return instance;
    }


    private String validate(List<Validator> validatorList) {
        for (Validator validator : validatorList) {
            if (!validator.validate()) {
                return validator.getErrorMessage();
            }
        }
        return "";
    }

    public String validateAddress(String s) {
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new AddressEmptyStringValidator(s));
        validatorList.add(new AddressTooShortValidator(s));
        validatorList.add(new AddressTooLongValidator(s));
        return validate(validatorList);
    }

    public String validateCity(String s) {
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new CityEmptyStringValidator(s));
        validatorList.add(new CityTooShortValidator(s));
        validatorList.add(new CityTooLongValidator(s));
        return validate(validatorList);
    }

    public String validatePostalCode(String s) {
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new PostalCodeEmptyStringValidator(s));
        validatorList.add(new PostalCodeTooShortValidator(s));
        validatorList.add(new PostalCodeTooLongValidator(s));
        return validate(validatorList);
    }

    public String validateUnitName(String s) {
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new UnitNameEmptyStringValidator(s));
        validatorList.add(new UnitNameTooShortValidator(s));
        validatorList.add(new UnitNameTooLongValidator(s));
        return validate(validatorList);
    }

    public String validatePoBox(String s) {
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new PoBoxEmptyStringValidator(s));
        validatorList.add(new PoBoxTooShortValidator(s));
        validatorList.add(new PoBoxTooLongValidator(s));
        return validate(validatorList);
    }

    public String validateUnitNumber(String s) {
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new UnitNumberEmptyStringValidator(s));
        validatorList.add(new UnitNumberTooShortValidator(s));
        validatorList.add(new UnitNumberTooLongValidator(s));
        return validate(validatorList);
    }

    public String validateRentAmount(int i) {
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new RentAmountIsZeroValidator(i));
        validatorList.add(new RentAmountTooLowValidator(i));
        validatorList.add(new RentAmountTooHighValidator(i));

        return validate(validatorList);
    }

    public String validateRentMadePayable(String s) {
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new RentMadePayableEmptyStringValidator(s));
        validatorList.add(new RentMadePayableTooShortValidator(s));
        validatorList.add(new RentMadePayableTooLongValidator(s));
        return validate(validatorList);
    }

    public String validateParkingSpaces(int i) {
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new ParkingSpaceTooLowValidator(i));
        validatorList.add(new ParkingSpacesTooHighValidator(i));
        return validate(validatorList);
    }

    public String validateParkingAmount(int i) {
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new ParkingAmountTooLowValidator(i));
        validatorList.add(new ParkingAmountTooHighValidator(i));
        return validate(validatorList);
    }

    public String validateStorageAmount(int i) {
        List<Validator> validatorList = new ArrayList<>();
        validatorList.add(new StorageAmountTooLowValidator(i));
        validatorList.add(new StorageAmountTooHighValidator(i));
        return validate(validatorList);
    }





}
