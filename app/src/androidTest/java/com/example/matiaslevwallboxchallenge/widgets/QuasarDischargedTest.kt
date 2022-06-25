package com.example.matiaslevwallboxchallenge.widgets

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.example.matiaslevwallboxchallenge.utils.BaseAndroidTest
import org.junit.Test
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.widgets.QuasarDischarged
import com.example.matiaslevwallboxchallenge.ui.widgets.Utils

class QuasarDischargedTest : BaseAndroidTest() {

    private val power = 38.732

    @Test
    fun quasarDischarged_SupplyingBuilding() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                QuasarDischarged(
                    power = power,
                    quasarAction = QuasarAction.SupplyingBuilding
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.not_providing_building)).assertDoesNotExist()
        composeTestRule.onNodeWithText(
            Utils.decimalFormatOnlyShowDecimalIfNotZero.format(
                power
            )).assertIsDisplayed()
    }

    @Test
    fun quasarDischarged_NotDischarging() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                QuasarDischarged(
                    power = 0.00,
                    quasarAction = QuasarAction.Nothing
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.not_providing_building)).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            Utils.decimalFormatOnlyShowDecimalIfNotZero.format(
                power
            )).assertDoesNotExist()
    }
}