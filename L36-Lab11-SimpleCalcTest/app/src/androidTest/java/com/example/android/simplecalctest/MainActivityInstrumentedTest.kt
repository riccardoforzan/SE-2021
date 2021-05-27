package com.example.android.simplecalctest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    // ActivityScenarioRule launches a given activity before the test starts
    // and closes after the test, i.e., ActivityScenario.launch is automatically
    // called before each test, and ActivityScenario.close is automatically
    // called at test teardown.
    @get:Rule // "get:" ensures rule is public
    val rule : ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun addTwoNumbers() {
        // Espresso is used here. See
        // https://developer.android.com/training/testing/ui-testing/espresso-testing.html
        // https://developer.android.com/training/testing/espresso/basics#kotlin
        onView(withId(R.id.operand_one_edit_text))
            .perform(typeText("1.0"), closeSoftKeyboard())
        onView(withId(R.id.operand_two_edit_text))
            .perform(typeText("1.0"), closeSoftKeyboard())
        onView(withId(R.id.operation_add_btn))
            .perform(click())
        onView(withId(R.id.operation_result_text_view))
            .check(matches(withText("2.0")))
    }

    @Test
    fun divByZeroReturnsError() {
        onView(withId(R.id.operand_one_edit_text))
            .perform(typeText("32.0"), closeSoftKeyboard())
        onView(withId(R.id.operand_two_edit_text))
            .perform(typeText("0.0"), closeSoftKeyboard())
        onView(withId(R.id.operation_div_btn))
            .perform(click())
        onView(withId(R.id.operation_result_text_view))
            .check(matches(withText("Error")))
    }

    @Test
    fun recreateActivity() {
        // If a device is low on resources, the system might destroy the activity
        // and recreate it when the user returns to the app
        val scenario = rule.scenario
        scenario.recreate()
        //TODO test the activity restores state correctly
    }
}