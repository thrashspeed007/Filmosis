package com.example.filmosis


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`

import org.junit.Rule
import org.junit.Test
import org.junit.internal.matchers.TypeSafeMatcher
import org.junit.runner.RunWith

//Accediendo con la cuent de ka  kaiet123
@LargeTest
@RunWith(AndroidJUnit4::class)
class AccederCuentaGUIespresso {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(AuthActivity::class.java)

    @Test
    fun accederCuentaGUIespresso() {
        val materialButton = onView(
            allOf(
                withId(R.id.signUpButton), withText("CREAR CUENTA"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout2),
                        childAtPosition(
                            withId(R.id.mainLayout),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(
                withId(R.id.signUp_returnButton), withText("VOLVER"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.logInButton), withText("ACCEDER"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout2),
                        childAtPosition(
                            withId(R.id.mainLayout),
                            2
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.signUp_emailEditText),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("ka"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.signUp_passwordEditText),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("kaiet123"), closeSoftKeyboard())

        val materialButton4 = onView(
            allOf(
                withId(R.id.logInButton), withText("INICIAR SESI�N"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
