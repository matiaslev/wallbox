package com.example.data

import com.example.data.entities.LiveDataResponse
import com.example.domain.models.QuasarAction
import org.junit.Test

import org.junit.Assert.*

class LiveDataResponseTest {

    @Test
    fun `mapping to LiveData works as expected with negative quasarsPower`() {
        // Given
        val liveDataResponse = LiveDataResponse(
            solarPower = 7.827,
            quasarsPower = -38.732,
            gridPower = 80.475,
            buildingDemand = 127.0339999999999,
            systemSoc = 48.333333333333336,
            totalEnergy = 960,
            currentEnergy = 464.0
        )
        // When
        val liveData = liveDataResponse.toDomainModel()

        // Then
        assertEquals(liveData.absoluteQuasarsPower, 38.732, 0.1)
        assertEquals(liveData.quasarAction, QuasarAction.SupplyingBuilding)
    }

    @Test
    fun `mapping to LiveData works as expected with positive quasarsPower`() {
        // Given
        val liveDataResponse = LiveDataResponse(
            solarPower = 7.827,
            quasarsPower = 38.732,
            gridPower = 80.475,
            buildingDemand = 127.0339999999999,
            systemSoc = 48.333333333333336,
            totalEnergy = 960,
            currentEnergy = 464.0
        )
        // When
        val liveData = liveDataResponse.toDomainModel()

        // Then
        assertEquals(liveData.absoluteQuasarsPower, 38.732, 0.1)
        assertEquals(liveData.quasarAction, QuasarAction.ChargingCar)
    }

    @Test
    fun `mapping to LiveData works as expected with zero quasarsPower`() {
        // Given
        val liveDataResponse = LiveDataResponse(
            solarPower = 7.827,
            quasarsPower = 0.00,
            gridPower = 80.475,
            buildingDemand = 127.0339999999999,
            systemSoc = 48.333333333333336,
            totalEnergy = 960,
            currentEnergy = 464.0
        )
        // When
        val liveData = liveDataResponse.toDomainModel()

        // Then
        assertEquals(liveData.absoluteQuasarsPower, 0.00, 0.1)
        assertEquals(liveData.quasarAction, QuasarAction.Nothing)
    }
}