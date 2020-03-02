package com.example.ryan.roomrep;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.ryan.roomrep.Factories.AbstractFactory;
import com.example.ryan.roomrep.Factories.FactoryType;
import com.example.ryan.roomrep.Factories.TextInputFactory;
import com.example.ryan.roomrep.LandlordFragments.AddHouseFragment;
import com.example.ryan.roomrep.LandlordFragments.EditHouseDetailFragment;
import com.example.ryan.roomrep.LandlordFragments.HousesFragment;
import com.example.ryan.roomrep.TextInput.AutoCompleteTextView.AutoCompleteTextInput;
import com.example.ryan.roomrep.TextInput.TextInput;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.test.annotation.UiThreadTest;
import androidx.test.espresso.Espresso.*;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.ViewAction.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;


@RunWith(AndroidJUnit4ClassRunner.class)
public class TestAddHouseFragment {


    @Rule
    public ActivityTestRule<MainActivityLandlord> activityRule = new ActivityTestRule(MainActivityLandlord.class);

    private MainActivityLandlord activity;

    @Before
    public void setup(){
        activity = activityRule.getActivity();
    }


    @Test
    public void AddressTest() {





    }
}
