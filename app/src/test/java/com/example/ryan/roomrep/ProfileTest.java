package com.example.ryan.roomrep;

import com.example.ryan.roomrep.Classes.Profile.Profile;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ProfileTest {


    @Test
    public void TestFirstNameEmpty() {
        Profile profile = new Profile("", "fdsafsaddf", "fdsafadsf", "fdsafdasdf");
        Map<Integer, String> validator = profile.getValidator();
        Assert.assertEquals("error with first name empty validation", validator.get(0), "Please enter a first name.");
    }
    @Test
    public void TestFirstNameValid() {
        Profile profile = new Profile("dfasdfa", "faffdsadf", "dsafasdf", "adsfaasfads");
        Map<Integer, String> validator = profile.getValidator();
        Assert.assertEquals("error with first name empty validation", validator.get(0), "");
    }

    @Test
    public void TestLastNameEmpty() {
        Profile profile = new Profile("fdsadfdsa", "", "fdsafadsf", "fdsafdasdf");
        Map<Integer, String> validator = profile.getValidator();
        Assert.assertEquals("error with last name empty validation", validator.get(1), "Please enter a last name.");
    }
    @Test
    public void TestLastNameValid() {
        Profile profile = new Profile("dfasdfa", "faffdsadf", "dsafasdf", "adsfaasfads");
        Map<Integer, String> validator = profile.getValidator();
        Assert.assertEquals("error with first name empty validation", validator.get(1), "");
    }

    @Test
    public void TestEmailEmpty() {
        Profile profile = new Profile("fdsadfdsa", "dsafdsaf", "", "fdsafdasdf");
        Map<Integer, String> validator = profile.getValidator();
        Assert.assertEquals("error with email empty validation", validator.get(2), "Please enter an email.");
    }

    @Test
    public void TestEmailValid() {
        Profile profile = new Profile("dfasdfa", "faffdsadf", "dsafasdf", "adsfaasfads");
        Map<Integer, String> validator = profile.getValidator();
        Assert.assertEquals("error with first name empty validation", validator.get(2), "");
    }


    @Test
    public void TestBioEmpty() {
        Profile profile = new Profile("fdsadfdsa", "dsafdsaf", "fdsafads", "");
        Map<Integer, String> validator = profile.getValidator();
        Assert.assertEquals("error with bio empty validation", validator.get(3), "Please enter a bio. Tell your landlord who you are.");
    }

    @Test
    public void TestBio200CharacterLimit() {
        Profile profile = new Profile("fdsadfdsa", "dsafdsaf", "fdsafads", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaal");
        Map<Integer, String> validator = profile.getValidator();
        Assert.assertEquals("error with bio character limit validation", validator.get(4), "Bio too long. Please limit to 200 characters");
    }

    @Test
    public void TestBioValid() {
        Profile profile = new Profile("dfasdfa", "faffdsadf", "dsafasdf", "adsfaasfads");
        Map<Integer, String> validator = profile.getValidator();
        Assert.assertEquals("error with first name empty validation", validator.get(3), "");
    }








}