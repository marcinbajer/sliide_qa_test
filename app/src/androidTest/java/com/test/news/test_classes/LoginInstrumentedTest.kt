package com.test.news.test_classes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.test.news.R
import com.test.news.annotations.LoginTests
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.view_objects.*
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import android.content.Intent
import androidx.test.espresso.Espresso.pressBackUnconditionally


class LoginInstrumentedTest {


    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Test
    fun shouldLoginWithValidCredentials() {
        onView(withId(R.id.editTextUserName))
            .perform(clearText(), typeText(VALID_USER_NAME))
        onView(withId(R.id.editTextPassword))
            .perform(clearText(), typeText(VALID_USER_PASSWORD))
        onView(withId(R.id.buttonLogin))
            .perform(click())

        // TODO assert login when ready
        assertTrue(activityTestRule.activity.isFinishing)
    }

    // Scenario I
    @LoginTests
    @Test
    fun loginViewAppear() {
        assertLogoAppear()
        assertUserNameInputAppear()
        assertPasswordInputAppear()
        assertLoginButtonAppear()
    }

    // Scenario II
    @LoginTests
    @Test
    fun loginWithWrongUserName() {
        enterUsername("wrongUserName")
        enterPassword(VALID_USER_PASSWORD)
        clickLoginButton()

        // TODO: Add assertion for error icon & error message, couldn't handle this elements atm.
    }

    @LoginTests
    @Test
    fun loginWithWrongPassword() {
        enterUsername(VALID_USER_NAME)
        enterPassword("wrongPassword")
        clickLoginButton()

        // TODO: Add assertion for error icon & error message, couldn't handle this elements atm.
    }

    // Scenario III
    @LoginTests
    @Test
    fun loginSucceed() {
        enterUsername(VALID_USER_NAME)
        enterPassword(VALID_USER_PASSWORD)
        clickLoginButton()

        assertNewsFeedAppear()
    }

    // Scenario IV
    @LoginTests
    @Test
    fun userAlreadyLoggedInAppClosed() {
        enterUsername(VALID_USER_NAME)
        enterPassword(VALID_USER_PASSWORD)
        clickLoginButton()
        pressBackUnconditionally() // closing an application by clicking system back button

        assertNewsFeedAppear()
    }

    @LoginTests
    @Test
    fun userAlreadyLoggedInAppKilled() {
        enterUsername(VALID_USER_NAME)
        enterPassword(VALID_USER_PASSWORD)
        clickLoginButton()
        activityTestRule.finishActivity() // killing an application by finishing activity
        activityTestRule.launchActivity(Intent()) // returning app activity

        assertNewsFeedAppear()

    }

    companion object {
        private const val VALID_USER_NAME = "user1"
        private const val VALID_USER_PASSWORD = "password"
    }
}
