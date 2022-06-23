package com.example.matiaslevwallboxchallenge.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme

@Composable
fun Quasar(
    modifier: Modifier = Modifier,
    power: Double
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
                text = if (power > 0) {
                    stringResource(id = R.string.quasars_charging_card_from_grid)
                } else stringResource(id = R.string.quasars_supplying_building),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )

            Row(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                QuasarCharged(power = power)
                QuasarDischarged(power = power)
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_4)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.NEXUS_5)
private fun Preview() {
    MatiasLevWallboxChallengeTheme {
        Column {
            Quasar(power = -38.732)
            Quasar(power = 38.732)
        }
    }
}