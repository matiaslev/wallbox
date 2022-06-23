package com.example.matiaslevwallboxchallenge.ui.screens.historical_data

import com.example.domain.models.HistoricalDataItem
import com.github.mikephil.charting.data.Entry
import java.time.ZoneOffset
import java.time.ZonedDateTime

fun HistoricalDataItem.toSolarPanelPowerEntry() = Entry(
    ZonedDateTime.parse(timestamp).toEpochSecond().toFloat(),
    pvActivePower.toFloat()
)

fun HistoricalDataItem.toGridPowerEntry() = Entry(
    ZonedDateTime.parse(timestamp).toEpochSecond().toFloat(),
    gridActivePower.toFloat()
)

fun HistoricalDataItem.toQuasarsActivePowerEntry() = Entry(
    ZonedDateTime.parse(timestamp).toEpochSecond().toFloat(),
    /**
     * If the value is negative it means it is discharging to the building
     * to supply the building demand
     * **
     * we are only showing sources that supplies the building energy demand as on
     * the widget that navigates to this screen
     */
    if (quasarsActivePower < 0) quasarsActivePower.toFloat() * -1 else 0f
)