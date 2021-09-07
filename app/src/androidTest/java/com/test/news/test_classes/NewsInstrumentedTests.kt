package com.test.news.test_classes

import androidx.test.rule.ActivityTestRule
import com.test.news.annotations.NewsTests
import org.junit.Rule
import org.junit.Test
import com.test.news.utils.Utils.Companion.disableWifiAndMobileNetwork
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.utils.Utils.Companion.enableWifiAndMobileNetwork
import com.test.news.view_objects.*


class NewsInstrumentedTests {

    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    // Scenario 1
    @NewsTests
    @Test
    fun newsImagesAreLoaded() {
        enableWifiAndMobileNetwork()
        enterUsername(VALID_USER_NAME)
        enterPassword(VALID_USER_PASSWORD)
        clickLoginButton()
        assertNewsFeedAppear()
    }

    // Scenario 2
    @NewsTests
    @Test
    fun newsImagesAreNotLoaded() {
        disableWifiAndMobileNetwork()
        enterUsername(VALID_USER_NAME)
        enterPassword(VALID_USER_PASSWORD)
        clickLoginButton()

        assertFailedToLoadErrorAppear()
        // TODO: Add assertion for Retry button when it's ready
    }

    // Scenario 3
    @NewsTests
    @Test
    fun newsImageIsOpenedInBrowser() {
        enableWifiAndMobileNetwork()
        enterUsername(VALID_USER_NAME)
        enterPassword(VALID_USER_PASSWORD)
        clickLoginButton()
        clickOnFirstImage()
        // To be honest I don't know how to check if browser is opened :(
        // I need to get deeper into the handling activities

        // TODO: Assertion for browser handling
    }

    companion object {
        private const val VALID_USER_NAME = "user1"
        private const val VALID_USER_PASSWORD = "password"
    }
}