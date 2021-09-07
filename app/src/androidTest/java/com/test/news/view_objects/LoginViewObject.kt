package com.test.news.view_objects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.test.news.R
import com.test.news.view_objects.LoginViewObject.loginButton
import com.test.news.view_objects.LoginViewObject.logo
import com.test.news.view_objects.LoginViewObject.passwordInput
import com.test.news.view_objects.LoginViewObject.userNameInput

object LoginViewObject {
    // ID's for elements available on the Login view
    val logo: Int = R.id.textViewLogo
    val userNameInput: Int = R.id.editTextUserName
    val passwordInput: Int = R.id.editTextPassword
    val loginButton: Int = R.id.buttonLogin
}

//              ACTIONS

// This function is used for entering username to the proper field on the Login view
fun enterUsername(username: String) {
    onView(withId(userNameInput))
        .perform(
            ViewActions.clearText(),
            ViewActions.typeText(username)
        )
}

// This function is used for entering password to the proper field on the Login view
fun enterPassword(password: String) {
    onView(withId(passwordInput))
        .perform(
            ViewActions.clearText(),
            ViewActions.typeText(password)
        )
}

// This function is used for submitting login form
fun clickLoginButton() {
    onView(withId(loginButton))
        .perform(ViewActions.click())
}

//              ASSERTIONS

// This function check if Logo on the login view appear
fun assertLogoAppear() {
    onView(withId(logo)).check(ViewAssertions.matches(isDisplayed()))
}

// This function check if input field for user name on the login view appear
fun assertUserNameInputAppear() {
    onView(withId(userNameInput)).check(ViewAssertions.matches(isDisplayed()))
}

// This function check if input field for user password on the login view appear
fun assertPasswordInputAppear() {
    onView(withId(passwordInput)).check(ViewAssertions.matches(isDisplayed()))
}

// This function check if login button on the login view appear
fun assertLoginButtonAppear() {
    onView(withId(loginButton)).check(ViewAssertions.matches(isDisplayed()))
}