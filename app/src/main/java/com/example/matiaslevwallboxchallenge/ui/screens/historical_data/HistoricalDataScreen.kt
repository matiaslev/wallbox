package com.example.matiaslevwallboxchallenge.ui.screens.historical_data

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.models.HistoricalDataItem
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import me.bytebeats.views.charts.line.LineChart
import me.bytebeats.views.charts.line.LineChartData
import me.bytebeats.views.charts.line.render.line.SolidLineDrawer
import me.bytebeats.views.charts.line.render.point.EmptyPointDrawer
import me.bytebeats.views.charts.line.render.point.FilledCircularPointDrawer
import me.bytebeats.views.charts.line.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.line.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.views.charts.simpleChartAnimation
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
    if (state.historicalData != null) {
        AnimatedVisibility(visible = state.historicalData.isNotEmpty()) {
            LineChartView(state.historicalData)
        }
    }
}

@Composable
fun LineChartView(
    historicalData: List<HistoricalDataItem>
) {
    LineChart(
        lineChartData = LineChartData(
            points = historicalData.map { it.toSolarPanelCharPoint() }
        ),
        // Optional properties.
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp),
        animation = simpleChartAnimation(),
        pointDrawer = EmptyPointDrawer,
        lineDrawer = SolidLineDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(),
        horizontalOffset = 5f
    )
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