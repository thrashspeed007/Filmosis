package com.example.filmosis


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
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
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//crear cuenta y accedemos da error por que la cuenta ya existe
@LargeTest
@RunWith(AndroidJUnit4::class)
class CrearCuentaGUI {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(AuthActivity::class.java)

    @Test
    fun crearCuentaGUI() {
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

        val appCompatEditText = onView(
            allOf(
                withId(R.id.signUp_usernameEditText),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout4),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("kaiet"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.signUp_emailEditText),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout4),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("kaietlamasoneira@gmail.com"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.signUp_passwordEditText),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout4),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("kaiet123"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.signUp_nameEditText),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout4),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(replaceText("kaiet"), closeSoftKeyboard())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.signUp_surnameEditText),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout4),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText("jimenez"), closeSoftKeyboard())

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.signUp_bornDateEditText),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout4),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(click())

        val materialTextView = onView(
            allOf(
                withClassName(`is`("com.google.android.material.textview.MaterialTextView")),
                withText("2024"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())

        val materialTextView2 = onView(
            allOf(
                withClassName(`is`("com.google.android.material.textview.MaterialTextView")),
                withText("2024"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialTextView2.perform(click())

        val materialTextView3 = onData(anything())
            .inAdapterView(
                allOf(
                    withClassName(`is`("android.widget.YearPickerView")),
                    childAtPosition(
                        withClassName(`is`("com.android.internal.widget.DialogViewAnimator")),
                        1
                    )
                )
            )
            .atPosition(36)
        materialTextView3.perform(scrollTo(), click())

        val materialButton2 = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton2.perform(scrollTo(), click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.signUp_signUpButton), withText("CREAR CUENTA"),
                childAtPosition(
                    allOf(
                        withId(R.id.linearLayout4),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            0
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())
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
