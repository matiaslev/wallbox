package com.example.matiaslevwallboxchallenge.ui.widgets

import android.content.Context
import com.example.domain.models.LiveData
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.R
import com.github.mikephil.charting.data.PieEntry

fun LiveData.getPieChartPowerValues(context: Context): List<PieEntry> {
    val values = mutableListOf<PieEntry>()

    if (shouldAddSolarPower()) values.add(toSolarPowerPieEntry(context))
    if (shouldAddGridPower()) values.add(toGridPowerPieEntry(context))
    if (shouldAddQuasarsPower()) values.add(toQuasarsPowerPieEntry(context))

    return values
}

private fun LiveData.toSolarPowerPieEntry(context: Context) = PieEntry(
    solarPower.toFloat(),
    context.getString(R.string.solar_power)
)

private fun LiveData.shouldAddSolarPower() = solarPower > 0

private fun LiveData.toGridPowerPieEntry(context: Context) = PieEntry(
    gridPower.toFloat(),
    context.getString(R.string.grid_power)
)

private fun LiveData.shouldAddGridPower() = gridPower > 0

private fun LiveData.toQuasarsPowerPieEntry(context: Context) = PieEntry(
    absoluteQuasarsPower.toFloat(),
    context.getString(R.string.quasars_supplying_building)
)

private fun LiveData.shouldAddQuasarsPower() =
    quasarAction == QuasarAction.SupplyingBuilding && absoluteQuasarsPower > 0

fun Boolean.getTextColor(context: Context) = if (this) {
    context.getColor(R.color.white)
} else context.getColor(R.color.black)

fun Boolean.getPieChartCenterColor(context: Context) = if (this) {
    context.getColor(R.color.black)
} else context.getColor(R.color.white)