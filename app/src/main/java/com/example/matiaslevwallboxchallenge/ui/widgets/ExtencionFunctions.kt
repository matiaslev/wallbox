package com.example.matiaslevwallboxchallenge.ui.widgets

import android.content.Context
import com.example.domain.models.LiveData
import com.example.matiaslevwallboxchallenge.R
import com.github.mikephil.charting.data.PieEntry

fun LiveData.toSolarPowerPieEntry() = PieEntry(
    solarPower.toFloat(),
    "Solar Power"
)

fun LiveData.toGridPowerPieEntry() = PieEntry(
    gridPower.toFloat(),
    "Grid Power"
)

fun LiveData.toQuasarsPowerPieEntry() = PieEntry(
    absoluteQuasarsPower.toFloat(),
    "Quasar Power"
)

fun Boolean.getTextColor(context: Context) = if (this) {
    context.getColor(R.color.white)
} else context.getColor(R.color.black)

fun Boolean.getPieChartCenterColor(context: Context) = if (this) {
    context.getColor(R.color.black)
} else context.getColor(R.color.white)