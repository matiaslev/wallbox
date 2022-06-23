package com.example.matiaslevwallboxchallenge.ui.screens.historical_data

import android.content.res.Configuration
import android.graphics.Color
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.domain.models.HistoricalDataItem
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import org.koin.androidx.compose.get
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

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
    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        factory = { context ->
            LineChart(context).apply {
                data = LineData(
                    // Solar Panel
                    LineDataSet(
                        historicalData.map { it.toSolarPanelPowerEntry() },
                        context.getString(R.string.solar_panels)
                    ).apply {
                        setDrawCircles(false)
                        lineWidth = 3f
                        setDrawFilled(false)
                        color = Color.YELLOW
                    },
                    // Grid
                    LineDataSet(
                        historicalData.map { it.toGridPowerEntry() },
                        context.getString(R.string.from_the_grid)
                    ).apply {
                        setDrawCircles(false)
                        lineWidth = 3f
                        setDrawFilled(false)
                        color = Color.GREEN
                    },
                    // Quasars
                    LineDataSet(
                        historicalData.map { it.toQuasarsActivePowerEntry() },
                        context.getString(R.string.quasars_supplying_building)
                    ).apply {
                        setDrawCircles(false)
                        lineWidth = 3f
                        setDrawFilled(false)
                        color = Color.BLUE
                    },
                )

                xAxis.apply {
                    setDrawGridLines(false)
                    position = XAxis.XAxisPosition.BOTTOM
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            //val date = Instant.ofEpochMilli(value.toLong() * 1000).atZone(ZoneOffset.of("+00:00")).toLocalDate();
                            // val date = Date(value.toLong() * 1000)
                            val date: LocalDateTime = LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(value.toLong() * 1000),
                                ZoneOffset.of("+00:00")
                            )
                            return getChartDateFormatHourOfDay(date)
                        }
                    }
                }

                axisRight.apply {
                    setDrawGridLines(false)
                }

                axisLeft.isEnabled = false
                setDrawBorders(false)

                description.text = ""

                animateX(1400, Easing.EaseInOutQuad)
            }
        }
    )
}

fun getChartDateFormatHourOfDay(date: LocalDateTime): String {
    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd hh a")
    return date.format(dateTimeFormatter)
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.NEXUS_5)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
private fun Preview() {
    MatiasLevWallboxChallengeTheme {
        HistoricalDataScreen(
            state = HistoricalDataViewModel.ViewState(
                isLoading = false,
                historicalData = listOf(
                    HistoricalDataItem(
                        buildingActivePower = 40.47342857142857,
                        gridActivePower = 44.234380952380945,
                        pvActivePower = 0.0,
                        quasarsActivePower = 3.7609523809523817,
                        timestamp = "2021-09-26T22:01:00+00:00"
                    ),
                    HistoricalDataItem(
                        buildingActivePower = 41.04429999999999,
                        gridActivePower = 47.47763333333334,
                        pvActivePower = 0.0,
                        quasarsActivePower = 6.432999999999996,
                        timestamp = "2021-09-26T22:02:00+00:00"
                    )
                )
            )
        )
    }
}