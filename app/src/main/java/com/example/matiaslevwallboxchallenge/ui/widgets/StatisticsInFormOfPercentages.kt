package com.example.matiaslevwallboxchallenge.ui.widgets

import android.content.res.Configuration
import android.graphics.Color
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.domain.models.LiveData
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
                        setColors(Color.YELLOW, Color.GREEN, Color.BLUE)
                    }
                )

                pieChart.setUsePercentValues(true)
                pieChart.description.isEnabled = false
                pieChart.rotationAngle = 0f
                pieChart.setEntryLabelColor(Color.BLACK)
                pieChart.setEntryLabelTextSize(18f)

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
            liveData = previewLiveDataMock(),
            onClick = { },
            animateChart = false
        )
    }
}