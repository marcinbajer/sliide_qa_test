package com.test.news.view_objects

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.test.news.R
import com.test.news.utils.Utils.Companion.clickElementAtPosition
import com.test.news.utils.Utils.Companion.waitUntil
import com.test.news.view_objects.NewsViewObject.loadErrorText
import com.test.news.view_objects.NewsViewObject.newsContent
import com.test.news.view_objects.NewsViewObject.newsImage

object NewsViewObject {

    // ID's for elements available on the News view
    val newsContent: Int = R.id.recyclerViewNews
    val newsImage: Int = R.id.imageView
    val loadErrorText: String = "Failed to load news"
}

//              ACTIONS

// This function clicks on the first image on the News feed.
fun clickOnFirstImage() {
    waitUntil { clickElementAtPosition(newsImage,0) }
}

//              ASSERTIONS

// This function check if news feed appear on the News view
fun assertNewsFeedAppear() {
    waitUntil { onView(withId(newsContent)).check(matches(isDisplayed())) }
}

// This function check if proper error message appear on the News feed whe Internet is off
fun assertFailedToLoadErrorAppear() {
    waitUntil { onView(withText(loadErrorText)).check(matches(isDisplayed())) }
}
