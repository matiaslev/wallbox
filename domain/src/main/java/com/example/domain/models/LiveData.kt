package com.example.domain.models

data class LiveData(
    val solarPower: Double,
    val quasarsPower: Double,
    val gridPower: Double,
    val buildingDemand: Double,
    val systemSoc: Double,
    val totalEnergy: Int,
    val currentEnergy: Double
)