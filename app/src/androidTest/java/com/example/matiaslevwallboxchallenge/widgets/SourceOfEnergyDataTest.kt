package com.example.matiaslevwallboxchallenge.widgets

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.example.domain.mock.MockDomainData
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.example.matiaslevwallboxchallenge.ui.widgets.SourceOfEnergyData
import com.example.matiaslevwallboxchallenge.utils.BaseAndroidTest
import org.junit.Test

class SourceOfEnergyDataTest : BaseAndroidTest() {

    @Test
    fun sourceOfEnergyData_SupplyingBuilding() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                SourceOfEnergyData(
                    liveData = MockDomainData.liveDataMock()
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.building_demand)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.solar_power)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.grid_power)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.quasars_supplying_building)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.quasars_charging_card_from_grid)).assertDoesNotExist()
        composeTestRule.onNodeWithText(context.getString(R.string.not_transfering_energy)).assertDoesNotExist()
        composeTestRule.onNodeWithText("0").assertDoesNotExist()
    }

    @Test
    fun sourceOfEnergyData_ChargingCar() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                SourceOfEnergyData(
                    liveData = MockDomainData.liveDataMock(
                        quasarAction = QuasarAction.ChargingCar
                    )
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.building_demand)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.solar_power)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.grid_power)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.quasars_supplying_building)).assertDoesNotExist()
        composeTestRule.onNodeWithText(context.getString(R.string.quasars_charging_card_from_grid)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.not_transfering_energy)).assertDoesNotExist()
        composeTestRule.onNodeWithText("0").assertDoesNotExist()
    }

    @Test
    fun sourceOfEnergyData_Nothing() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                SourceOfEnergyData(
                    liveData = MockDomainData.liveDataMock(
                        quasarAction = QuasarAction.Nothing,
                        absoluteQuasar = 0.00
                    )
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.building_demand)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.solar_power)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.grid_power)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.quasars_supplying_building)).assertDoesNotExist()
        composeTestRule.onNodeWithText(context.getString(R.string.quasars_charging_card_from_grid)).assertDoesNotExist()
        composeTestRule.onNodeWithText(context.getString(R.string.not_transfering_energy)).assertIsDisplayed()
        composeTestRule.onNodeWithText("0").assertIsDisplayed()
    }
}