package com.example.matiaslevwallboxchallenge.widgets

import androidx.compose.ui.test.assertIsDisplayed
import com.example.matiaslevwallboxchallenge.R
import androidx.compose.ui.test.onNodeWithText
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.example.matiaslevwallboxchallenge.ui.widgets.Quasar
import com.example.matiaslevwallboxchallenge.ui.widgets.Utils
import com.example.matiaslevwallboxchallenge.utils.BaseAndroidTest
import org.junit.Test

class QuasarTest : BaseAndroidTest() {

    private val power = 38.732

    @Test
    fun quasar_SupplyingBuilding() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                Quasar(
                    power = power,
                    quasarAction = QuasarAction.SupplyingBuilding
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.quasars_supplying_building)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.not_charging_car)).assertIsDisplayed()
        composeTestRule.onNodeWithText("38.73" + context.getString(R.string.kw)).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            Utils.decimalFormatOnlyShowDecimalIfNotZero.format(
                power
            ) + context.getString(R.string.kw)
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(context.getString(R.string.not_providing_building))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.getString(R.string.quasars_charging_card_from_grid))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.getString(R.string.quasars)).assertDoesNotExist()
    }

    @Test
    fun quasar_ChargingCar() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                Quasar(
                    power = power,
                    quasarAction = QuasarAction.ChargingCar
                )
            }
        }

        composeTestRule.onNodeWithText(context.getString(R.string.not_providing_building))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.quasars_charging_card_from_grid))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("38.73" + context.getString(R.string.kw)).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            Utils.decimalFormatOnlyShowDecimalIfNotZero.format(
                power
            ) + context.getString(R.string.kw)
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(context.getString(R.string.quasars_supplying_building))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.getString(R.string.not_charging_car))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.getString(R.string.quasars)).assertDoesNotExist()
    }

    @Test
    fun quasar_Nothing() {
        composeTestRule.setContent {
            MatiasLevWallboxChallengeTheme {
                Quasar(
                    power = 0.00,
                    quasarAction = QuasarAction.Nothing
                )
            }
        }

        Thread.sleep(3000)

        composeTestRule.onNodeWithText(context.getString(R.string.quasars)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.not_providing_building))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.not_charging_car))
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(
            Utils.decimalFormatOnlyShowDecimalIfNotZero.format(
                power
            )
        ).assertDoesNotExist()

        composeTestRule.onNodeWithText(context.getString(R.string.quasars_charging_card_from_grid))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(context.getString(R.string.quasars_supplying_building))
            .assertDoesNotExist()

    }
}