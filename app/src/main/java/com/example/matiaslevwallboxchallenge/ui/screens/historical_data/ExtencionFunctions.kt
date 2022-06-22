package com.example.matiaslevwallboxchallenge.ui.screens.historical_data

import com.example.domain.models.HistoricalDataItem
import me.bytebeats.views.charts.line.LineChartData

fun HistoricalDataItem.toSolarPanelCharPoint() = LineChartData.Point(
    pvActivePower.toFloat(), timestamp
)