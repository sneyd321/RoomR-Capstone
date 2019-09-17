package com.example.ryan.roomrep;

import com.example.ryan.roomrep.Classes.Landlord.Landlord;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

public class TestLandlord {



    @Test
    public void FirstNameEmptyTest(){
        Landlord landlord = new Landlord("", "Sneyd", "aaaaaa", "aaaaaa", "a@s.com");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in first name empty string validation.", map.get(0), "Please enter your first name.");
    }

    @Ignore
    public void FirstNameInvalidTest() {
        Landlord landlord = new Landlord("fdafdsafads", "Sneyd", "aaaaaa", "aaaaaa", "a@s.com");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in first name regex validation.", map.get(1), "Please enter a valid first name.");
    }
    @Test
    public void FirstNameValidTest() {
        Landlord landlord = new Landlord("Ryan", "Sneyd", "aaaaaa", "aaaaaa", "a@s.com");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in first name regex validation.", map.get(0), "");
    }



    @Test
    public void LastNameEmptyTest(){
        Landlord landlord = new Landlord("Ryan", "", "aaaaaa", "aaaaaa", "a@s.com");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in last name empty string validation.", map.get(2), "Please enter your last name.");
    }

    @Ignore
    public void LastNameInvalidTest() {
        Landlord landlord = new Landlord("Ryan", "fdsa", "aaaaaa", "aaaaaa", "a@s.com");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in last name regex validation.", map.get(2), "Please enter a valid last name.");
    }
    @Test
    public void LastNameValidTest() {
        Landlord landlord = new Landlord("Ryan", "Sneyd", "aaaaaa", "aaaaaa", "a@s.com");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in last name regex validation.", map.get(0), "");
    }

    @Test
    public void PasswordEmptyTest(){
        Landlord landlord = new Landlord("Ryan", "Sneyd", "", "aaaaaa", "a@s.com");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in password empty string validation.", map.get(4), "Please enter a password.");
    }

    @Test
    public void PasswordNotLongEnoughTest(){
        Landlord landlord = new Landlord("Ryan", "Sneyd", "aa", "aaaaaa", "a@s.com");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in password empty string validation.", map.get(5), "Please enter a password longer than six characters.");
    }

    @Test
    public void PasswordValidTest() {
        Landlord landlord = new Landlord("Ryan", "Sneyd", "aaaaaa", "aaaaaa", "a@s.com");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in password validation.", map.get(5), "");
    }

    @Test
    public void Password2EmptyTest()  {
        Landlord landlord = new Landlord("Ryan", "Sneyd", "aaaaaa", "", "a@s.com");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in password2 validation.", map.get(6), "Please re-type your password.");
    }
    @Test
    public void PasswordsDoNotMatchTest() {
        Landlord landlord = new Landlord("Ryan", "Sneyd", "aaaaaa", "bbbbbb", "a@s.com");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in password matching validation.", map.get(7), "Passwords do not match.");
    }
    @Test
    public void PasswordsMatchTest() {
        Landlord landlord = new Landlord("Ryan", "Sneyd", "aaaaaa", "aaaaaa", "a@s.com");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in password validation.", map.get(7), "");
    }
    @Test
    public void EmailEmptyTest() {
        Landlord landlord = new Landlord("Ryan", "Sneyd", "aaaaaa", "aaaaaa", "");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in email validation.", map.get(8), "Please enter an email address.");
    }
    @Test
    public void EmailValidTest() {
        Landlord landlord = new Landlord("Ryan", "Sneyd", "aaaaaa", "aaaaaa", "a@s.com");
        Map<Integer, String> map = landlord.getLandlordValidator();
        Assert.assertEquals("Error in email validation.", map.get(8), "");
    }




}
