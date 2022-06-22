package com.example.matiaslevwallboxchallenge.ui.screens.historical_data

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import org.koin.androidx.compose.get

@Composable
fun HistoricalDataScreen(
    viewModel: HistoricalDataViewModel = get()
) {
    LaunchedEffect(key1 = viewModel) { viewModel.loadData() }

    HistoricalDataScreen(viewModel.state)
}

@Composable
fun HistoricalDataScreen(
    state: HistoricalDataViewModel.ViewState
) {

}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun Preview() {
    MatiasLevWallboxChallengeTheme {
        HistoricalDataScreen(
            state = HistoricalDataViewModel.ViewState(

            )
        )
    }
}