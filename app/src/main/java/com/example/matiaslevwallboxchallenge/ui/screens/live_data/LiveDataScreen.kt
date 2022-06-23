package com.example.matiaslevwallboxchallenge.ui.screens.live_data

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.domain.mock.MockDomainData
import com.example.matiaslevwallboxchallenge.ui.Screens
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.example.matiaslevwallboxchallenge.ui.widgets.Quasar
import com.example.matiaslevwallboxchallenge.ui.widgets.SourceOfEnergyData
import com.example.matiaslevwallboxchallenge.ui.widgets.StaticInspectionCompanionProvider
import org.koin.androidx.compose.get

@Composable
fun LiveDataScreen(
    navController: NavController,
    viewModel: LiveDataViewModel = get()
) {
    LaunchedEffect(key1 = viewModel) { viewModel.loadData() }

    LiveDataScreen(
        state = viewModel.state
    ) {
        navController.navigate(Screens.HistoricalData.name)
    }
}

@Composable
fun LiveDataScreen(
    state: LiveDataViewModel.ViewState,
    animateChart: Boolean = true,
    onNavigateToHistoricalData: () -> Unit
) {
    AnimatedVisibility(visible = state.isLoading) {

    }

    AnimatedVisibility(visible = state.isLoading.not()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            state.liveData?.let { liveData ->
                Quasar(power = liveData.absoluteQuasarsPower)

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
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO)
private fun Preview() {
    MatiasLevWallboxChallengeTheme {
        LiveDataScreen(
            state = previewLiveDataScreenMock(),
            animateChart = false,
            onNavigateToHistoricalData = { }
        )
    }
}

fun previewLiveDataScreenMock() = LiveDataViewModel.ViewState(
    isLoading = false,
    liveData = MockDomainData.liveDataMock()
)