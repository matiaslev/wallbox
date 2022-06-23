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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.models.LiveData
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme

@Composable
fun SourceOfEnergyData(
    modifier: Modifier = Modifier,
    liveData: LiveData
) {
    Card(
        modifier = Modifier
            .padding(16.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.building_demand))
                Text(
                    text = Utils.decimalFormatOnlyShowDecimalIfNotZero.format(liveData.buildingDemand)
                )
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (liveData.quasarsPower > 0) {
                        stringResource(id = R.string.quasars_charging_card_from_grid)
                    } else stringResource(id = R.string.quasars_supplying_building)
                )
                Text(
                    text = if (liveData.quasarsPower > 0) {
                        Utils.decimalFormatOnlyShowDecimalIfNotZero.format(liveData.quasarsPower)
                    } else Utils.decimalFormatOnlyShowDecimalIfNotZero.format(liveData.quasarsPower * -1)
                )
            }

            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(
                        modifier = Modifier
                            .padding(end = 16.dp),
                        text = stringResource(id = R.string.solar_power)
                    )
                    Text(
                        text = Utils.decimalFormatOnlyShowDecimalIfNotZero.format(liveData.solarPower)
                    )
                }
                Row {
                    Text(
                        modifier = Modifier
                            .padding(end = 16.dp),
                        text = stringResource(id = R.string.grid_power)
                    )
                    Text(
                        text = Utils.decimalFormatOnlyShowDecimalIfNotZero.format(liveData.gridPower)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun Preview() {
    MatiasLevWallboxChallengeTheme {
        SourceOfEnergyData(
            liveData = previewLiveDataMock()
        )
    }
}

fun previewLiveDataMock() = LiveData(
    solarPower = 7.827,
    quasarsPower = -38.732,
    gridPower = 80.475,
    buildingDemand = 127.03399999999999,
    systemSoc = 48.333333333333336,
    totalEnergy = 960,
    currentEnergy = 464.0
)