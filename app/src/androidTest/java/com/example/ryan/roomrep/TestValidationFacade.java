package com.example.ryan.roomrep;

import android.content.Context;

import com.example.ryan.roomrep.Classes.App;
import com.example.ryan.roomrep.Classes.Validation.ValidationFacade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static org.mockito.Mockito.mock;

public class TestValidationFacade {

    ValidationFacade facade ;
    @Rule
    public ActivityTestRule<MainActivityLandlord> activityRule = new ActivityTestRule(MainActivityLandlord.class);

    private MainActivityLandlord activity;
    @Before
    public void setup() {
        activity = activityRule.getActivity();
        facade = ValidationFacade.getInstance();
    }

    @Test
    public void TestValidationFacadeEmptyStringAddress() {
        String expected = activity.getString(R.string.empty_address_error);
        Assert.assertEquals("Error in empty string validation.", facade.validateAddress(""), expected);
    }

    @Test
    public void TestValidationFacadeTooLongUpperBoundAddress() {
        String expected = activity.getString(R.string.address_too_long_error) + " " + 50 + " " + activity.getString(R.string.too_string_error_postfix);
        Assert.assertEquals("Error in string length (Too Long).", facade.validateAddress("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"), expected);
    }


    @Test
    public void TestValidationFacadeTooLongAddress() {
        String expected = "";
        Assert.assertEquals("Error in string length (Too Long).", facade.validateAddress("cfghjkl,bvcdfghjkkbvcxdrtyujkmcxdrtyujkmc123rt$$$%"), expected);
    }

    @Test
    public void TestValidationFacadeAddress() {
        String expected = "";
        Assert.assertEquals("Error in string validation (Something went wrong in general).", facade.validateAddress("123 Address St."), expected);
    }

    @Test
    public void TestValidationFacadeTooShortLowerBoundAddress() {
        String expected = activity.getString(R.string.address_too_short_error) + " " + 3 + " " + activity.getString(R.string.too_string_error_postfix);
        Assert.assertEquals("Error in string length (Too Short).", facade.validateAddress("1"), expected);
    }

    @Test
    public void TestValidationFacadeTooShortAddress() {
        String expected = "";
        Assert.assertEquals("Error in string length (Too Short).", facade.validateAddress("123"), expected);
    }


    @Test
    public void TestValidationFacadeTooHighUpperBoundRentAmount() {
        String expected = activity.getString(R.string.rent_amount_too_long_error) + " " + 2000 + activity.getString(R.string.too_number_error_postfix);
        Assert.assertEquals("Error in number size (Too High).", facade.validateRentAmount(3000), expected);
    }


    @Test
    public void TestValidationFacadeTooHighRentAmount() {
        String expected = "";
        Assert.assertEquals("Error in number size (Too High).", facade.validateRentAmount(2000), expected);
    }

    @Test
    public void TestValidationFacadeRentAmount() {
        String expected = "";
        Assert.assertEquals("Error in number validation (Something went wrong in general).", facade.validateRentAmount(200), expected);
    }

    @Test
    public void TestValidationFacadeTooLowLowerBoundRentAmount() {
        String expected = activity.getString(R.string.rent_amount_too_short_error) + " " + 1 + activity.getString(R.string.too_number_error_postfix);
        Assert.assertEquals("Error in number size (Too Low).", facade.validateRentAmount(-1), expected);
    }

    @Test
    public void TestValidationFacadeTooLowRentAmount() {
        String expected = "";
        Assert.assertEquals("Error in number size (Too Low).", facade.validateRentAmount(1), expected);
    }

    @Test
    public void TestValidationFacadeIsZero() {
        String expected = activity.getString(R.string.rent_amount_zero_empty);
        Assert.assertEquals("Error is zero check", facade.validateRentAmount(0), expected);
    }


}

