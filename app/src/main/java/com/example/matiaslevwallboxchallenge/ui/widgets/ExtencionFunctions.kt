package com.example.matiaslevwallboxchallenge.ui.widgets

import com.example.domain.models.LiveData
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