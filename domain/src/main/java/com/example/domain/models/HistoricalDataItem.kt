package com.example.domain.models

data class HistoricalDataItem(
    val buildingActivePower: Double,
    val gridActivePower: Double,
    val pvActivePower: Double,
    val quasarsActivePower: Double,
    val timestamp: String
)