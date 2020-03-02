package com.example.ryan.roomrep;

import com.example.ryan.roomrep.Classes.Validation.InputSanitizer;
import com.example.ryan.roomrep.Classes.Validation.ValidationFacade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

public class TestInputSanitizer {


    private InputSanitizer inputSanitizer;

    @Before
    public void setup() {

        inputSanitizer = InputSanitizer.getInstance();
    }

    @Test
    public void TestInputSanitizerWithLeadingAndEndingWhiteSpace() {
        String expected = "123 Address St";
        Assert.assertEquals("Error in trim() method", expected ,inputSanitizer.sanitizeString("     123 Address St      "));
    }

    @Test
    public void TestInputSanitizerCapitalizeAfterInnerWhiteSpace() {
        String expected = "123 Address St";
        Assert.assertEquals("Error in with the string builder or capitalization method", expected ,inputSanitizer.sanitizeString("123 address st"));
    }

    @Test
    public void TestInputSanitizerWithEmptyInput() {
        String expected = "";
        Assert.assertEquals("Error in empty string check", expected ,inputSanitizer.sanitizeString(""));
    }

    @Test
    public void TestInputSanitizerWithLeadingNumbers() {
        int expected = 57;
        Assert.assertEquals("Error in integer leading zeros check", expected ,inputSanitizer.sanitizeInt("0000057"));
    }


}
