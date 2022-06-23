package com.example.matiaslevwallboxchallenge

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import com.example.domain.mock.MockDomainData
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.ui.screens.live_data.LiveDataScreen
import com.example.matiaslevwallboxchallenge.ui.screens.live_data.LiveDataViewModel
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.example.matiaslevwallboxchallenge.ui.widgets.Utils
import org.junit.Rule
import org.junit.Test

class LiveDataScreenScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val absoluteQuasar = 38.732

    @Test
    fun liveData_SupplyingBuilding_state() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                LiveDataScreen(
                    state = LiveDataViewModel.ViewState(
                        isLoading = false,
                        liveData = MockDomainData.liveDataMock(
                            absoluteQuasar = absoluteQuasar
                        )
                    ),
                    animateChart = false,
                    onNavigateToHistoricalData = {}
                )
            }
        }

        /**
         * Draw once in Quasar Widget and once in SourceOfEnergyData Widget
         */
        composeTestRule.onAllNodesWithText(
            Utils.decimalFormatOnlyShowDecimalIfNotZero.format(
                absoluteQuasar
            )
        ).assertCountEquals(2)

        /**
         * Draw once in Quasar Widget and once in SourceOfEnergyData Widget
         */
        composeTestRule.onAllNodesWithText("Quasars supplying building").assertCountEquals(2)


        composeTestRule.onNodeWithText("not charging car").assertIsDisplayed()
    }

    @Test
    fun liveData_ChargingCar_state() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                LiveDataScreen(
                    state = LiveDataViewModel.ViewState(
                        isLoading = false,
                        liveData = MockDomainData.liveDataMock(
                            absoluteQuasar = absoluteQuasar,
                            quasarAction = QuasarAction.ChargingCar
                        )
                    ),
                    animateChart = false,
                    onNavigateToHistoricalData = {}
                )
            }
        }

        /**
         * Draw once in Quasar Widget and once in SourceOfEnergyData Widget
         */
        composeTestRule.onAllNodesWithText(
            Utils.decimalFormatOnlyShowDecimalIfNotZero.format(
                absoluteQuasar
            )
        ).assertCountEquals(2)

        /**
         * Draw once in Quasar Widget and once in SourceOfEnergyData Widget
         */
        composeTestRule.onAllNodesWithText("Charging car from grid").assertCountEquals(2)


        composeTestRule.onNodeWithText("not providing building").assertIsDisplayed()
    }

    @Test
    fun liveData_Nothing_state() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                LiveDataScreen(
                    state = LiveDataViewModel.ViewState(
                        isLoading = false,
                        liveData = MockDomainData.liveDataMock(
                            absoluteQuasar = 0.00,
                            quasarAction = QuasarAction.Nothing
                        )
                    ),
                    animateChart = false,
                    onNavigateToHistoricalData = {}
                )
            }
        }

        composeTestRule.onNodeWithText("not charging car").assertIsDisplayed()
        composeTestRule.onNodeWithText("not providing building").assertIsDisplayed()
    }
}