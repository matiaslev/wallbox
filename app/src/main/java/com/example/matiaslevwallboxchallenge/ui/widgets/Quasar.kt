package com.example.matiaslevwallboxchallenge.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme

@Composable
fun Quasar(
    modifier: Modifier = Modifier,
    power: Double,
    quasarAction: QuasarAction
) {
    Card(
        modifier = modifier
            .padding(16.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = when (quasarAction) {
                    QuasarAction.ChargingCar -> stringResource(id = R.string.quasars_charging_card_from_grid)
                    QuasarAction.SupplyingBuilding -> stringResource(id = R.string.quasars_supplying_building)
                    QuasarAction.Nothing -> stringResource(id = R.string.quasars)
                },
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )

            Row(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                QuasarCharged(
                    power = power,
                    quasarAction = quasarAction
                )
                Spacer(modifier = Modifier.width(16.dp))
                QuasarDischarged(
                    power = power,
                    quasarAction = quasarAction
                )
            }
        }
    }
}

@Composable
@Preview(
    name = "SupplyingBuilding Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_4
)
@Preview(
    name = "SupplyingBuilding Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.NEXUS_5
)
private fun PreviewSupplyingBuilding() {
    MatiasLevWallboxChallengeTheme {
        Column {
            Quasar(power = 38.732, quasarAction = QuasarAction.SupplyingBuilding)
        }
    }
}

@Composable
@Preview(
    name = "ChargingCar Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_4
)
@Preview(
    name = "ChargingCar Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.NEXUS_5
)
private fun PreviewChargingCar() {
    MatiasLevWallboxChallengeTheme {
        Column {
            Quasar(power = 38.732, quasarAction = QuasarAction.ChargingCar)
        }
    }
}

@Composable
@Preview(
    name = "Nothing Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_4
)
@Preview(
    name = "Nothing Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.NEXUS_5
)
private fun PreviewNothing() {
    MatiasLevWallboxChallengeTheme {
        Column {
            Quasar(power = 38.732, quasarAction = QuasarAction.Nothing)
        }
    }
}