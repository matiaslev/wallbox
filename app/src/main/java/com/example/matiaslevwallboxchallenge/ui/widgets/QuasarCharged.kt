package com.example.matiaslevwallboxchallenge.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme

@Composable
fun QuasarCharged(
    power: Double,
    quasarAction: QuasarAction
) {
    Box(
        modifier = Modifier
            .defaultMinSize(minHeight = 80.dp)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.primary)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (quasarAction == QuasarAction.ChargingCar) {
            Text(
                modifier = Modifier,
                text = Utils.decimalFormatOnlyShowDecimalIfNotZero.format(power),
                style = MaterialTheme.typography.h4
            )
        } else {
            Text(
                text = stringResource(id = R.string.not_charging_car),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview(
    name = "SupplyingBuilding Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "SupplyingBuilding Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun PreviewSupplyingBuilding() {
    MatiasLevWallboxChallengeTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            QuasarCharged(power = 38.7320, quasarAction = QuasarAction.SupplyingBuilding)
        }
    }
}

@Composable
@Preview(
    name = "ChargingCar Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "ChargingCar Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun PreviewChargingCar() {
    MatiasLevWallboxChallengeTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            QuasarCharged(power = 38.7320, quasarAction = QuasarAction.ChargingCar)
        }
    }
}

@Composable
@Preview(
    name = "SupplyingBuilding Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "SupplyingBuilding Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun PreviewNothing() {
    MatiasLevWallboxChallengeTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            QuasarCharged(power = 38.7320, quasarAction = QuasarAction.Nothing)
        }
    }
}