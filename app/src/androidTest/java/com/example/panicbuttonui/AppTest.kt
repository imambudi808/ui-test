package com.example.panicbuttonui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by imambudi808 on 06/02/2021.
 */

@RunWith(AndroidJUnit4::class)
class AppTest {

    //untuk pengujian ini tolong aplikasi berada pada posisi onresume/sudah melewati on create
    @get:Rule
    var mActivityRule: ActivityScenarioRule<LoginActivity?>? = ActivityScenarioRule(
        LoginActivity::class.java
    )

    //test scenario (isi edittext phonenumber dan nama-> clik button masuk -> clik dial call button
    @Test
    fun testLogin(){

        onView(withId(R.id.edtPhone))
            .perform(typeText("081717366252"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.edtName))
            .perform(typeText("M. Imam Budi"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.btnLogin)).perform(click())

        onView(withId(R.id.btnCallCenter)).perform(click())
    }
}