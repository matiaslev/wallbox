package com.example.matiaslevwallboxchallenge.ui.widgets

import android.content.Context
import com.example.domain.models.LiveData
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.R
import com.github.mikephil.charting.data.PieEntry

fun LiveData.getPieChartPowerValues(): List<PieEntry> {
    val values = mutableListOf<PieEntry>()

    if (shouldAddSolarPower()) values.add(toSolarPowerPieEntry())
    if (shouldAddGridPower()) values.add(toGridPowerPieEntry())
    if (shouldAddQuasarsPower()) values.add(toQuasarsPowerPieEntry())

    return values
}

private fun LiveData.toSolarPowerPieEntry() = PieEntry(
    solarPower.toFloat(),
    "Solar Power"
)

private fun LiveData.shouldAddSolarPower() = solarPower > 0

private fun LiveData.toGridPowerPieEntry() = PieEntry(
    gridPower.toFloat(),
    "Grid Power"
)

private fun LiveData.shouldAddGridPower() = gridPower > 0

private fun LiveData.toQuasarsPowerPieEntry() = PieEntry(
    absoluteQuasarsPower.toFloat(),
    "Quasar Power"
)

private fun LiveData.shouldAddQuasarsPower() =
    quasarAction == QuasarAction.SupplyingBuilding && absoluteQuasarsPower > 0

fun Boolean.getTextColor(context: Context) = if (this) {
    context.getColor(R.color.white)
} else context.getColor(R.color.black)

fun Boolean.getPieChartCenterColor(context: Context) = if (this) {
    context.getColor(R.color.black)
} else context.getColor(R.color.white)