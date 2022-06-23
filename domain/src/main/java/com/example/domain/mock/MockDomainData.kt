package com.example.domain.mock

import com.example.domain.models.HistoricalDataItem
import com.example.domain.models.LiveData
import com.example.domain.models.QuasarAction

object MockDomainData {
    fun liveDataMock(
        absoluteQuasar: Double = 38.732,
        action: QuasarAction = QuasarAction.SupplyingBuilding
    ) = LiveData(
        solarPower = 7.827,
        absoluteQuasarsPower = absoluteQuasar,
        quasarAction = action,
        gridPower = 80.475,
        buildingDemand = 127.03399999999999,
        systemSoc = 48.333333333333336,
        totalEnergy = 960,
        currentEnergy = 464.0
    )

    fun historicalDataMock() = listOf(
        HistoricalDataItem(
            buildingActivePower = 40.47342857142857,
            gridActivePower = 44.234380952380945,
            pvActivePower = 0.0,
            quasarsActivePower = 3.7609523809523817,
            timestamp = "2021-09-26T22:01:00+00:00"
        ),
        HistoricalDataItem(
            buildingActivePower = 41.04429999999999,
            gridActivePower = 47.47763333333334,
            pvActivePower = 0.0,
            quasarsActivePower = 6.432999999999996,
            timestamp = "2021-09-26T22:02:00+00:00"
        )
    )
}