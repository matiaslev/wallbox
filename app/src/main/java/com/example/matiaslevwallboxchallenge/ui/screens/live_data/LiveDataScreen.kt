package com.example.matiaslevwallboxchallenge.ui.screens.live_data

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.domain.mock.MockDomainData
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.Screens
import com.example.matiaslevwallboxchallenge.ui.base.LastIntention
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.example.matiaslevwallboxchallenge.ui.widgets.Quasar
import com.example.matiaslevwallboxchallenge.ui.widgets.SourceOfEnergyData
import com.example.matiaslevwallboxchallenge.ui.widgets.StaticInspectionCompanionProvider
import com.example.matiaslevwallboxchallenge.ui.widgets.base.ContentState
import com.example.matiaslevwallboxchallenge.ui.widgets.base.ViewStateType
import org.koin.androidx.compose.get

@Composable
fun LiveDataScreen(
    navController: NavController,
    viewModel: LiveDataViewModel = get()
) {
    LaunchedEffect(key1 = viewModel) { viewModel.loadData() }

    LiveDataScreen(
        state = viewModel.state,
        onNavigateToHistoricalData = { navController.navigate(Screens.HistoricalData.name) },
        lastIntention = viewModel.lastIntention
    )
}

@Composable
fun LiveDataScreen(
    state: LiveDataViewModel.ViewState,
    animateChart: Boolean = true,
    onNavigateToHistoricalData: () -> Unit,
    lastIntention: LastIntention
) {
    ContentState(
        state = state.viewStateType,
        lastIntention = lastIntention
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            state.liveData?.let { liveData ->
                Quasar(
                    power = liveData.absoluteQuasarsPower,
                    quasarAction = liveData.quasarAction
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.state_of_your_energy),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6
                )
                SourceOfEnergyData(liveData = liveData)

                StaticInspectionCompanionProvider(
                    liveData = liveData,
                    onClick = onNavigateToHistoricalData,
                    animateChart = animateChart
                )
            }
        }
    }
}

@Composable
@Preview(
    name = "SupplyingBuilding Dark",
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "SupplyingBuilding Light",
    uiMode = UI_MODE_NIGHT_NO
)
private fun PreviewSupplyingBuilding() {
    MatiasLevWallboxChallengeTheme {
        LiveDataScreen(
            state = previewLiveDataScreenMock(
                quasarAction = QuasarAction.SupplyingBuilding
            ),
            animateChart = false,
            onNavigateToHistoricalData = { },
            lastIntention = null
        )
    }
}

@Composable
@Preview(
    name = "ChargingCar Dark",
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "ChargingCar Light",
    uiMode = UI_MODE_NIGHT_NO
)
private fun PreviewChargingCar() {
    MatiasLevWallboxChallengeTheme {
        LiveDataScreen(
            state = previewLiveDataScreenMock(
                quasarAction = QuasarAction.ChargingCar
            ),
            animateChart = false,
            onNavigateToHistoricalData = { },
            lastIntention = null
        )
    }
}

@Composable
@Preview(
    name = "Nothing Dark",
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "Nothing Light",
    uiMode = UI_MODE_NIGHT_NO
)
private fun Preview() {
    MatiasLevWallboxChallengeTheme {
        LiveDataScreen(
            state = previewLiveDataScreenMock(
                absoluteQuasar = 0.00,
                quasarAction = QuasarAction.Nothing
            ),
            animateChart = false,
            onNavigateToHistoricalData = { },
            lastIntention = null
        )
    }
}

fun previewLiveDataScreenMock(
    absoluteQuasar: Double = 38.732,
    quasarAction: QuasarAction
) = LiveDataViewModel.ViewState(
    viewStateType = ViewStateType.Success,
    liveData = MockDomainData.liveDataMock(
        absoluteQuasar = absoluteQuasar,
        quasarAction = quasarAction
    )
)