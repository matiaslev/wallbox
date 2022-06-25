package com.example.matiaslevwallboxchallenge.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.domain.mock.MockDomainData
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.screens.historical_data.HistoricalDataScreen
import com.example.matiaslevwallboxchallenge.ui.screens.historical_data.HistoricalDataViewModel
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.example.matiaslevwallboxchallenge.ui.widgets.base.ViewStateType
import com.example.matiaslevwallboxchallenge.utils.BaseAndroidTest
import org.junit.Test

class HistoricalDataScreenTest : BaseAndroidTest() {

    @Test
    fun historicalData_LineChartView_isDisplayed() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                HistoricalDataScreen(
                    state = HistoricalDataViewModel.ViewState(
                        viewStateType = ViewStateType.Success,
                        historicalData = MockDomainData.historicalDataMock()
                    ),
                    onBackButtonPressed =  { },
                    lastIntention = { }
                )
            }
        }

        composeTestRule.onNodeWithTag("LineChartView").assertIsDisplayed()
    }

    @Test
    fun historicalData_LineChartView_isDisplayed_onError() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                HistoricalDataScreen(
                    state = HistoricalDataViewModel.ViewState(
                        viewStateType = ViewStateType.Error(""),
                        historicalData = null
                    ),
                    onBackButtonPressed =  { },
                    lastIntention = { }
                )
            }
        }

        composeTestRule.onNodeWithTag("LineChartView").assertDoesNotExist()
    }

    @Test
    fun historicalData_lastIntention_isBeingAdded() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                HistoricalDataScreen(
                    state = HistoricalDataViewModel.ViewState(
                        viewStateType = ViewStateType.NetworkError,
                        historicalData = null
                    ),
                    onBackButtonPressed =  { },
                    lastIntention = { }
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.try_again)).assertIsDisplayed()
    }
}