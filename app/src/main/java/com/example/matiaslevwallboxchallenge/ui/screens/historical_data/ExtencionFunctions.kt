package com.example.matiaslevwallboxchallenge.ui.screens.historical_data

import com.example.domain.models.HistoricalDataItem
import com.github.mikephil.charting.data.Entry
import java.time.ZonedDateTime

fun HistoricalDataItem.toSolarPanelPowerEntry() = Entry(
    ZonedDateTime.parse(timestamp).toEpochSecond().toFloat(),
    pvActivePower.toFloat()
)

fun HistoricalDataItem.toGridPowerEntry() = Entry(
    ZonedDateTime.parse(timestamp).toEpochSecond().toFloat(),
    gridActivePower.toFloat()
)