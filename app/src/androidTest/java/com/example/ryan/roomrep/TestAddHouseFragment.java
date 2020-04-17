package com.example.ryan.roomrep;

import android.view.View;

import com.example.ryan.roomrep.Activities.MainActivityLandlord;
import com.example.ryan.roomrep.Classes.Validation.ValidationFacade;
import com.example.ryan.roomrep.App.Factories.AbstractFactory;
import com.example.ryan.roomrep.App.Factories.FactoryType;
import com.example.ryan.roomrep.App.Factories.TextInputFactory;
import com.example.ryan.roomrep.LandlordFragments.AddHouseFragment;
import com.example.ryan.roomrep.LandlordFragments.EditHouseDetailFragment;
import com.example.ryan.roomrep.App.TextInput.TextInput;
import com.google.android.material.textfield.TextInputLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
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
