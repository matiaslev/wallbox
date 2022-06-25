package com.example.matiaslevwallboxchallenge.utils

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule

open class BaseAndroidTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
}