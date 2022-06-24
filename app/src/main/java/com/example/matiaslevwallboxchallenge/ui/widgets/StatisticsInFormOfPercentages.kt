package com.example.matiaslevwallboxchallenge.ui.widgets

import android.content.res.Configuration
import android.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.domain.mock.MockDomainData
import com.example.domain.models.LiveData
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

@Composable
fun StaticInspectionCompanionProvider(
    modifier: Modifier = Modifier
        .size(300.dp),
    liveData: LiveData,
    onClick: () -> Unit,
    animateChart: Boolean = true
) {
    val isDarkMode = isSystemInDarkTheme()
    AndroidView(
        modifier = modifier,
        factory = { context ->
            PieChart(context).also { pieChart ->
                pieChart.data = PieData(
                    PieDataSet(
                        mutableListOf(
                            liveData.toSolarPowerPieEntry(),
                            liveData.toGridPowerPieEntry(),
                            liveData.toQuasarsPowerPieEntry()
                        ),
                        "" // Not Used
                    ).apply {
                        valueFormatter = PercentFormatter(pieChart)
                        valueTextSize = 18f
                        setColors(
                            context.getColor(R.color.solar_energy),
                            context.getColor(R.color.grid_energy),
                            context.getColor(R.color.quasar_energy)
                        )
                    }
                )

                pieChart.setUsePercentValues(true)
                pieChart.description.isEnabled = false
                pieChart.rotationAngle = 0f
                pieChart.setEntryLabelColor(Color.BLACK)
                pieChart.setEntryLabelTextSize(18f)

                pieChart.legend.textColor = if (isDarkMode) {
                    context.getColor(R.color.white)
                } else context.getColor(R.color.black)

                pieChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                    override fun onValueSelected(e: Entry?, h: Highlight?) {
                        onClick()
                    }

                    override fun onNothingSelected() {
                        // Nothing to do
                    }
                })

                /**
                 * This animateChart is a workaround for add the Easing animation
                 * without breaking the preview
                 */
                if (animateChart) pieChart.animateY(1400, Easing.EaseInOutQuad)
            }
        }
    )
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun Preview() {
    MatiasLevWallboxChallengeTheme {
        StaticInspectionCompanionProvider(
            liveData = MockDomainData.liveDataMock(),
            onClick = { },
            animateChart = false
        )
    }
}