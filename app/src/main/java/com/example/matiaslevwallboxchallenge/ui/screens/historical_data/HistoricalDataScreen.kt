package com.example.matiaslevwallboxchallenge.ui.screens.historical_data

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.domain.mock.MockDomainData
import com.example.domain.models.HistoricalDataItem
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.example.matiaslevwallboxchallenge.ui.widgets.Utils
import com.example.matiaslevwallboxchallenge.ui.widgets.getTextColor
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
    val isDarkMode = isSystemInDarkTheme()
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
                        color = context.getColor(R.color.solar_energy)
                    },
                    // Grid
                    LineDataSet(
                        historicalData.map { it.toGridPowerEntry() },
                        context.getString(R.string.from_the_grid)
                    ).apply {
                        setDrawCircles(false)
                        lineWidth = 3f
                        setDrawFilled(false)
                        color = context.getColor(R.color.grid_energy)
                    },
                    // Quasars
                    LineDataSet(
                        historicalData.map { it.toQuasarsActivePowerEntry() },
                        context.getString(R.string.quasars_supplying_building)
                    ).apply {
                        setDrawCircles(false)
                        lineWidth = 3f
                        setDrawFilled(false)
                        color = context.getColor(R.color.quasar_energy)
                    },
                )

                xAxis.apply {
                    textColor = isDarkMode.getTextColor(context)
                    legend.textColor = if (isDarkMode) {
                        context.getColor(R.color.white)
                    } else context.getColor(R.color.black)

                    setDrawGridLines(false)
                    position = XAxis.XAxisPosition.BOTTOM
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            val date: LocalDateTime = LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(value.toLong() * 1000),
                                ZoneOffset.of("+00:00")
                            )
                            return getChartDateFormatHourOfDay(date)
                        }
                    }
                }

                axisRight.apply {
                    textColor = isDarkMode.getTextColor(context)
                    setDrawGridLines(false)
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return context.getString(
                                R.string.kw_value,
                                Utils.decimalFormatOnlyShowDecimalIfNotZero.format(value)
                            )
                        }
                    }
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
                historicalData = MockDomainData.historicalDataMock()
            )
        )
    }
}