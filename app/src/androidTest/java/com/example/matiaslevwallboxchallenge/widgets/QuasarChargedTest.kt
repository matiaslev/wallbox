package com.example.matiaslevwallboxchallenge.widgets

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.example.matiaslevwallboxchallenge.ui.widgets.QuasarCharged
import com.example.matiaslevwallboxchallenge.utils.BaseAndroidTest
import org.junit.Test
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.widgets.Utils

class QuasarChargedTest : BaseAndroidTest() {

    private val power = 38.732

    @Test
    fun quasarCharged_ChargingCar() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                QuasarCharged(
                    power = power,
                    quasarAction = QuasarAction.ChargingCar
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.not_charging_car)).assertDoesNotExist()
        composeTestRule.onNodeWithText(
            Utils.decimalFormatOnlyShowDecimalIfNotZero.format(
                power
            )).assertIsDisplayed()
    }

    @Test
    fun quasarCharged_NotCharging() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                QuasarCharged(
                    power = 0.00,
                    quasarAction = QuasarAction.Nothing
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.not_charging_car)).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            Utils.decimalFormatOnlyShowDecimalIfNotZero.format(
                power
            )).assertDoesNotExist()
    }
}