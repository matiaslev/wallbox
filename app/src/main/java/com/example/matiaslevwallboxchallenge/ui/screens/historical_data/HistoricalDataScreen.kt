package com.example.matiaslevwallboxchallenge.ui.screens.historical_data

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.domain.mock.MockDomainData
import com.example.domain.models.HistoricalDataItem
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.base.LastIntention
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.example.matiaslevwallboxchallenge.ui.widgets.HistoricalDataMarker
import com.example.matiaslevwallboxchallenge.ui.widgets.Utils
import com.example.matiaslevwallboxchallenge.ui.widgets.base.ContentState
import com.example.matiaslevwallboxchallenge.ui.widgets.base.ViewStateType
import com.example.matiaslevwallboxchallenge.ui.widgets.getTextColor
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import org.koin.androidx.compose.get
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HistoricalDataScreen(
    navController: NavController,
    viewModel: HistoricalDataViewModel = get()
) {
    LaunchedEffect(key1 = viewModel) { viewModel.loadData() }

    HistoricalDataScreen(
        state = viewModel.state,
        onBackButtonPressed = { navController.popBackStack() },
        lastIntention = viewModel.lastIntention
    )
}

@Composable
fun HistoricalDataScreen(
    state: HistoricalDataViewModel.ViewState,
    onBackButtonPressed: () -> Unit,
    lastIntention: LastIntention
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp,
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, null,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .clickable(onClick = onBackButtonPressed)
                    )
                },
                title = {
                    Text(
                        text = stringResource(R.string.supplying_building),
                        style = MaterialTheme.typography.h6
                    )
                }
            )
        },

        content = {
            ContentState(
                state = state.viewStateType,
                lastIntention = lastIntention
            ) {
                if (state.historicalData != null) {
                    AnimatedVisibility(visible = state.historicalData.isNotEmpty()) {
                        LineChartView(state.historicalData)
                    }
                }
            }
        }
    )
}

@Composable
fun LineChartView(
    historicalData: List<HistoricalDataItem>
) {
    val isDarkMode = isSystemInDarkTheme()
    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("LineChartView")
            .verticalScroll(rememberScrollState()),
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
                    textSize = 12f

                    legend.apply {
                        textColor = isDarkMode.getTextColor(context)
                        textSize = 16f
                        isWordWrapEnabled = true
                        extraBottomOffset = 16f
                        extraTopOffset = 16f
                    }

                    setDrawGridLines(true)
                    position = XAxis.XAxisPosition.BOTTOM
                    labelCount = 4
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return value.epochToFormattedLineChartDateTime().getChartDateFormat(context)
                        }
                    }
                }

                axisRight.apply {
                    textColor = isDarkMode.getTextColor(context)
                    setDrawGridLines(true)
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

                setScaleEnabled(false)
                marker = HistoricalDataMarker(
                    context  = context,
                    layoutResource =  R.layout.fragment_historical_data_marker
                )

                animateX(1400, Easing.EaseInOutQuad)
            }
        }
    )
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.NEXUS_5)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.PIXEL_4)
private fun Preview() {
    MatiasLevWallboxChallengeTheme {
        HistoricalDataScreen(
            state = HistoricalDataViewModel.ViewState(
                viewStateType = ViewStateType.Success,
                historicalData = MockDomainData.historicalDataMock()
            ),
            onBackButtonPressed = { },
            lastIntention = null
        )
    }
}