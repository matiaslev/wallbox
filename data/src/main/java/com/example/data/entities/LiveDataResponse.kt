package com.example.data.entities


import com.example.data.base.BaseEntity
import com.example.domain.models.LiveData
import com.google.gson.annotations.SerializedName

data class LiveDataResponse(
    @SerializedName("solar_power")
    val solarPower: Double,
    @SerializedName("quasars_power")
    val quasarsPower: Double,
    @SerializedName("grid_power")
    val gridPower: Double,
    @SerializedName("building_demand")
    val buildingDemand: Double,
    @SerializedName("system_soc")
    val systemSoc: Double,
    @SerializedName("total_energy")
    val totalEnergy: Int,
    @SerializedName("current_energy")
    val currentEnergy: Double
) : BaseEntity<LiveData> {
    override fun toDomainModel() = LiveData(
        solarPower,
        quasarsPower,
        gridPower,
        buildingDemand,
        systemSoc,
        totalEnergy,
        currentEnergy
    )
}