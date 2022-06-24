package com.example.matiaslevwallboxchallenge.base

import androidx.compose.material.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.example.matiaslevwallboxchallenge.ui.widgets.base.ContentState
import com.example.matiaslevwallboxchallenge.ui.widgets.base.ViewStateType
import org.junit.Rule
import org.junit.Test

class ContentStateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun contentState_Loading() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                ContentState(state = ViewStateType.Loading, lastIntention = null) {
                    Text(text = "should not be shown")
                }
            }
        }

        composeTestRule.onNodeWithText("should not be shown").assertDoesNotExist()
        composeTestRule.onNodeWithTag("loading.json").assertIsDisplayed()
    }

    @Test
    fun contentState_success() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                ContentState(state = ViewStateType.Success, lastIntention = null) {
                    Text(text = "testing content drawing on success ViewStateType")
                }
            }
        }

        composeTestRule.onNodeWithText("testing content drawing on success ViewStateType").assertIsDisplayed()
    }

    @Test
    fun contentState_error() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                ContentState(state = ViewStateType.Error("error message should be shown"), lastIntention = null) {
                    Text(text = "should not be shown")
                }
            }
        }

        composeTestRule.onNodeWithText("should not be shown").assertDoesNotExist()
        composeTestRule.onNodeWithText("error message should be shown").assertIsDisplayed()

        /**
         * checking if we are using the correct file for the lottie animation
         */
        composeTestRule.onNodeWithTag("error.json").assertIsDisplayed()
    }

    @Test
    fun contentState_NetworkErrorState() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                ContentState(state = ViewStateType.NetworkError, lastIntention = null) {
                    Text(text = "should not be shown")
                }
            }
        }

        composeTestRule.onNodeWithText("should not be shown").assertDoesNotExist()
        composeTestRule.onNodeWithText(context.getString(R.string.network_error)).assertIsDisplayed()

        /**
         * checking if we are using the correct file for the lottie animation
         */
        composeTestRule.onNodeWithTag("network_error.json").assertIsDisplayed()
    }

    @Test
    fun contentState_Empty() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                ContentState(state = ViewStateType.Empty, lastIntention = null) {
                    Text(text = "should not be shown")
                }
            }
        }

        composeTestRule.onNodeWithText("should not be shown").assertDoesNotExist()
        composeTestRule.onNodeWithText(context.getString(R.string.we_not_have_data_to_show_you_yet)).assertIsDisplayed()

        /**
         * checking if we are using the correct file for the lottie animation
         */
        composeTestRule.onNodeWithTag("empty.json").assertIsDisplayed()
    }
}