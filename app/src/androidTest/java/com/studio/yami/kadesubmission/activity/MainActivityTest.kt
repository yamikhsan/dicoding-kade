package com.studio.yami.kadesubmission.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.widget.AutoCompleteTextView
import com.studio.yami.kadesubmission.R
import com.studio.yami.kadesubmission.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activity = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource)
    }

    @Test
    fun searchActivityTest(){
        onView(withId(R.id.floating_btn_search)).perform(click())
        onView(withId(R.id.action_search)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(ViewActions.typeText("Liverpool\n"))
        onView(withId(R.id.rv_search)).check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource)
    }
}