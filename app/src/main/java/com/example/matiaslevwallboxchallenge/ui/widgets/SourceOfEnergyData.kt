package com.example.matiaslevwallboxchallenge.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.mock.MockDomainData
import com.example.domain.models.LiveData
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme

@Composable
fun SourceOfEnergyData(
    modifier: Modifier = Modifier,
    liveData: LiveData
) {
    Card(
        modifier = modifier
            .padding(16.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.building_demand))
                Text(
                    text = buildAnnotatedString {
                        append(Utils.decimalFormatOnlyShowDecimalIfNotZero.format(liveData.buildingDemand))
                        withStyle(style = SpanStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold)) {
                            append(stringResource(id = R.string.kw))
                        }
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = when (liveData.quasarAction) {
                        QuasarAction.ChargingCar -> stringResource(id = R.string.quasars_charging_card_from_grid)
                        QuasarAction.SupplyingBuilding -> stringResource(id = R.string.quasars_supplying_building)
                        QuasarAction.Nothing -> stringResource(id = R.string.not_transfering_energy)
                    }
                )
                Text(
                    text = buildAnnotatedString {
                        append(Utils.decimalFormatOnlyShowDecimalIfNotZero.format(liveData.absoluteQuasarsPower))
                        withStyle(style = SpanStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold)) {
                            append(stringResource(id = R.string.kw))
                        }
                    }
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(1.dp)
                    .background(MaterialTheme.colors.onSurface),
            )

            Row(
                modifier = Modifier
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
                        text = buildAnnotatedString {
                            append(Utils.decimalFormatOnlyShowDecimalIfNotZero.format(liveData.solarPower))
                            withStyle(style = SpanStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold)) {
                                append(stringResource(id = R.string.kw))
                            }
                        }
                    )
                }
                Row {
                    Text(
                        modifier = Modifier
                            .padding(end = 16.dp),
                        text = stringResource(id = R.string.grid_power)
                    )
                    Text(
                        text = buildAnnotatedString {
                            append(Utils.decimalFormatOnlyShowDecimalIfNotZero.format(liveData.gridPower))
                            withStyle(style = SpanStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold)) {
                                append(stringResource(id = R.string.kw))
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(name = "Quasar Action SupplyingBuilding", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Quasar Action SupplyingBuilding", uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewSupplyingBuilding() {
    MatiasLevWallboxChallengeTheme {
        SourceOfEnergyData(
            liveData = MockDomainData.liveDataMock()
        )
    }
}

@Composable
@Preview(name = "Quasar Action ChargingCar", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Quasar Action ChargingCar", uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewChargingCar() {
    MatiasLevWallboxChallengeTheme {
        SourceOfEnergyData(
            liveData = MockDomainData.liveDataMock(
                quasarAction = QuasarAction.ChargingCar
            )
        )
    }
}

@Composable
@Preview(name = "Quasar Action Nothing", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Quasar Action Nothing", uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewNothing() {
    MatiasLevWallboxChallengeTheme {
        SourceOfEnergyData(
            liveData = MockDomainData.liveDataMock(
                absoluteQuasar = 0.00,
                quasarAction = QuasarAction.Nothing
            )
        )
    }
}