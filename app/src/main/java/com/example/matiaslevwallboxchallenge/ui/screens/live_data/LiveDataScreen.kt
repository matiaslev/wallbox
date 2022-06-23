package com.example.matiaslevwallboxchallenge.ui.screens.live_data

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.domain.models.LiveData
import com.example.matiaslevwallboxchallenge.ui.Screens
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
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
                StaticInspectionCompanionProvider(
                    liveData = liveData,
                    onClick = onNavigateToHistoricalData
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
            state = LiveDataViewModel.ViewState(
                isLoading = false,
                liveData = LiveData(
                    solarPower = 7.827,
                    quasarsPower = -38.732,
                    gridPower = 80.475,
                    buildingDemand = 127.03399999999999,
                    systemSoc = 48.333333333333336,
                    totalEnergy = 960,
                    currentEnergy = 464.0
                )
            ),
            onNavigateToHistoricalData = { }
        )
    }
}