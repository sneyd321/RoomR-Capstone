package com.example.ryan.roomrep;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.ryan.roomrep.Classes.Validation.ValidationFacade;
import com.example.ryan.roomrep.Factories.AbstractFactory;
import com.example.ryan.roomrep.Factories.FactoryType;
import com.example.ryan.roomrep.Factories.TextInputFactory;
import com.example.ryan.roomrep.LandlordFragments.AddHouseFragment;
import com.example.ryan.roomrep.LandlordFragments.EditHouseDetailFragment;
import com.example.ryan.roomrep.LandlordFragments.HousesFragment;
import com.example.ryan.roomrep.TextInput.AutoCompleteTextView.AutoCompleteTextInput;
import com.example.ryan.roomrep.TextInput.TextInput;
import com.google.android.material.textfield.TextInputLayout;
import com.google.common.truth.Truth;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.SplittableRandom;

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
import androidx.test.espresso.Espresso;
import androidx.test.espresso.Espresso.*;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.ViewAction.*;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;


@RunWith(AndroidJUnit4ClassRunner.class)
public class TestAddHouseFragment {


    @Rule
    public ActivityTestRule<MainActivityLandlord> activityRule = new ActivityTestRule(MainActivityLandlord.class);

    private MainActivityLandlord activity;

    private ValidationFacade validationFacade;

    @Before
    public void setup(){
        activity = activityRule.getActivity();
        FragmentScenario.launchInContainer(AddHouseFragment.class);
        validationFacade = ValidationFacade.getInstance();
    }


    @Test
    public void TestTextInputEmptyOnFocusChange() {
        onView(withId(R.id.edtAddHouseAddress)).perform(typeText("1234"));
        onView(withId(R.id.edtAddHouseAddress)).perform(clearText());
        onView(withId(R.id.edtAddHouseCity)).perform(click());
        onView(withId(R.id.tilAddHouseAddress)).check(matches(hasTextInputLayoutErrorText("Please enter an address.", R.id.edtAddHouseAddress)));
    }


    @Test
    public void TestTextInputTooLowAtBoundary() {
        onView(withId(R.id.edtAddHouseAddress)).perform(typeText("123"));
        onView(withId(R.id.tilAddHouseAddress)).check(matches(hasTextInputLayoutErrorText("", R.id.edtAddHouseAddress)));
    }

    @Test
    public void TestTextInputTooLowLowerBound() {
        onView(withId(R.id.edtAddHouseAddress)).perform(typeText("12"));
        onView(withId(R.id.tilAddHouseAddress)).check(matches(hasTextInputLayoutErrorText("Please enter an address greater than 3 characters.", R.id.edtAddHouseAddress)));
    }

    @Test
    public void TestTextInputTooHighAtBoundary() {
        onView(withId(R.id.edtAddHouseAddress)).perform(typeText("aaaaaaaaaaaaaaaaaaaa"));
        onView(withId(R.id.tilAddHouseAddress)).check(matches(hasTextInputLayoutErrorText("", R.id.edtAddHouseAddress)));
    }

    @Test
    public void TestTextInputTooHighUpperBound() {
        onView(withId(R.id.edtAddHouseAddress)).perform(typeText("aaaaaaaaaaaaaaaaaaaaa"));
        onView(withId(R.id.tilAddHouseAddress)).check(matches(hasTextInputLayoutErrorText("Please enter an address less than 20 characters.", R.id.edtAddHouseAddress)));
    }

    @Test
    public void TestTextInputOnNormalInput() {
        onView(withId(R.id.edtAddHouseAddress)).perform(typeText("123 Address St."));
        onView(withId(R.id.tilAddHouseAddress)).check(matches(hasTextInputLayoutErrorText("", R.id.edtAddHouseAddress)));
    }






    public static Matcher<View> hasTextInputLayoutErrorText(final String expectedErrorText, final int resourceId) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                TextInputFactory factory = (TextInputFactory) AbstractFactory.getFactory(view, FactoryType.TextInput);
                TextInput textInput = factory.getTextInput(resourceId);
                String error = textInput.getError();
                if (error == null && expectedErrorText.isEmpty()){
                    return true;
                }
                if (error == null) {
                    return false;
                }


                return expectedErrorText.equals(error);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    public static Matcher<View> hasText(final String expectedText, int resourceId) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                TextInputFactory factory = (TextInputFactory) AbstractFactory.getFactory(view, FactoryType.TextInput);
                TextInput textInput = factory.getTextInput(resourceId);
                String text = textInput.getText();
                if (text == null && expectedText.isEmpty()){
                    return true;
                }
                if (text == null) {
                    return false;
                }


                return expectedText.equals(text);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
