package com.test.news.utils

import android.util.Log
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Description
import org.hamcrest.Matcher

class Utils {

    companion object {
        private const val MAX_TRIES_THRESHOLD = 500

        /*
        This function allows to check a certain number of attempts
        if assertion comes true for specified action.
         */
        fun waitUntil(assertion: () -> Unit): Boolean {
            for (i in 0..MAX_TRIES_THRESHOLD) {
                try {
                    assertion.invoke()
                    return true
                } catch (throwable: Throwable) {
                    Log.d("MAX_TRIES_THRESHOLD ", i.toString())
                    throwable.printStackTrace()
                    InstrumentationRegistry.getInstrumentation().waitForIdleSync()
                }
            }
            throw Error("WaitUntil failed assertion")
        }

        // This function allows to disable network connection for both Wi-fi and Mobile Data
        fun disableWifiAndMobileNetwork() {
            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("svc wifi disable")
            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("svc data disable")
        }

        // This function allows to enable network connection for both Wi-fi and Mobile Data
        fun enableWifiAndMobileNetwork() {
            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("svc wifi enable")
            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand("svc data enable")
        }

        // This function allows to specify the position of the element with proper Matcher
        fun <T> getElementAtPosition(matcher: Matcher<T>, position: Int) = object : Matcher<T> {
            var foundObject: Any? = null
            var counter = 0
            override fun describeTo(description: Description?) {
                description?.appendText("Element at hierarchy position " + position)
            }

            override fun matches(item: Any?): Boolean {
                if (foundObject != null && foundObject === item) return true
                if (matcher.matches(item)) {
                    if (counter == position) {
                        foundObject = item
                        counter++
                        return true
                    }
                    counter++
                }
                return false
            }

            override fun describeMismatch(item: Any?, mismatchDescription: Description?) {
                // no-op
            }

            override fun _dont_implement_Matcher___instead_extend_BaseMatcher_() {
                // no-op
            }
        }

        /*
        This function allows to localize and click element on the specified position
        when multiple elements in the hierarchy contains the same ID.
         */
        fun clickElementAtPosition(element: Int, position: Int) {
            Espresso.onView(
                getElementAtPosition(
                    ViewMatchers.withId(element), position
                )
            ).perform(ViewActions.click())
        }
    }
}