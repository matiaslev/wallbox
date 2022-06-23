package com.example.domain.models

data class LiveData(
    val solarPower: Double,
    val absoluteQuasarsPower: Double,
    val quasarAction: QuasarAction,
    val gridPower: Double,
    val buildingDemand: Double,
    val systemSoc: Double,
    val totalEnergy: Int,
    val currentEnergy: Double
)

enum class QuasarAction {
    ChargingCar, SupplyingBuilding, Nothing
}