package com.example.data


import com.example.data.base.BaseEntity
import com.example.domain.models.HistoricalDataItem
import com.google.gson.annotations.SerializedName

class HistoricalDataResponse : ArrayList<HistoricalDataResponse.HistoricalDataItemResponse>() {

    data class HistoricalDataItemResponse(
        @SerializedName("building_active_power")
        val buildingActivePower: Double,
        @SerializedName("grid_active_power")
        val gridActivePower: Double,
        @SerializedName("pv_active_power")
        val pvActivePower: Double,
        @SerializedName("quasars_active_power")
        val quasarsActivePower: Double,
        @SerializedName("timestamp")
        val timestamp: String
    ) : BaseEntity<HistoricalDataItem> {

        override fun toDomainModel() = HistoricalDataItem(
            buildingActivePower, gridActivePower, pvActivePower, quasarsActivePower, timestamp
        )
    }

}