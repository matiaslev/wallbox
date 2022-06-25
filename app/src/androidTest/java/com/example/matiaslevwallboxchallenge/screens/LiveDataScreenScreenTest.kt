package com.example.matiaslevwallboxchallenge.screens

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import com.example.domain.mock.MockDomainData
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.screens.live_data.LiveDataScreen
import com.example.matiaslevwallboxchallenge.ui.screens.live_data.LiveDataViewModel
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.example.matiaslevwallboxchallenge.ui.widgets.Utils
import com.example.matiaslevwallboxchallenge.ui.widgets.base.ViewStateType
import com.example.matiaslevwallboxchallenge.utils.BaseAndroidTest
import org.junit.Test

class LiveDataScreenScreenTest : BaseAndroidTest() {

    private val absoluteQuasar = 38.732

    @Test
    fun liveData_SupplyingBuilding_state() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                LiveDataScreen(
                    state = LiveDataViewModel.ViewState(
                        viewStateType = ViewStateType.Success,
                        liveData = MockDomainData.liveDataMock(
                            absoluteQuasar = absoluteQuasar
                        )
                    ),
                    animateChart = false,
                    onNavigateToHistoricalData = {},
                    lastIntention = null
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
        composeTestRule.onAllNodesWithText(context.getString(R.string.quasars_supplying_building)).assertCountEquals(2)


        composeTestRule.onNodeWithText(context.getString(R.string.not_charging_car)).assertIsDisplayed()
    }

    @Test
    fun liveData_ChargingCar_state() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                LiveDataScreen(
                    state = LiveDataViewModel.ViewState(
                        viewStateType = ViewStateType.Success,
                        liveData = MockDomainData.liveDataMock(
                            absoluteQuasar = absoluteQuasar,
                            quasarAction = QuasarAction.ChargingCar
                        )
                    ),
                    animateChart = false,
                    onNavigateToHistoricalData = {},
                    lastIntention = null
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
        composeTestRule.onAllNodesWithText(context.getString(R.string.quasars_charging_card_from_grid)).assertCountEquals(2)

        composeTestRule.onNodeWithText(context.getString(R.string.not_providing_building)).assertIsDisplayed()
    }

    @Test
    fun liveData_Nothing_state() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                LiveDataScreen(
                    state = LiveDataViewModel.ViewState(
                        viewStateType = ViewStateType.Success,
                        liveData = MockDomainData.liveDataMock(
                            absoluteQuasar = 0.00,
                            quasarAction = QuasarAction.Nothing
                        )
                    ),
                    animateChart = false,
                    onNavigateToHistoricalData = {},
                    lastIntention = null
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.not_charging_car)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.not_providing_building)).assertIsDisplayed()
    }

    @Test
    fun liveData_lastIntention_isBeingAdded() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                LiveDataScreen(
                    state = LiveDataViewModel.ViewState(
                        viewStateType = ViewStateType.NetworkError,
                        liveData = null
                    ),
                    animateChart = false,
                    onNavigateToHistoricalData = {},
                    lastIntention = { }
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.try_again)).assertIsDisplayed()
    }
}