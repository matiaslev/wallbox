package com.astropaycard.domain.actions

import com.astropaycard.domain.base.shouldBeEqualTo
import com.example.domain.actions.GetHistoricalData
import com.example.domain.base.ResultWrapper
import com.example.domain.models.HistoricalDataItem
import com.example.domain.repositories.ApiRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class GetHistoricalDataTest {
    private lateinit var getHistoricalData: GetHistoricalData

    @MockK
    internal lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getHistoricalData = GetHistoricalData(apiRepository)
    }

    @Test
    fun `should return ResultSuccess when GetHistoricalData returns Success`() = runTest {
        // given
        val historicalData = listOf(
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
        coEvery { apiRepository.getHistoricalData() } returns ResultWrapper.Success(historicalData)

        // when
        val result = getHistoricalData()

        // then
        result shouldBeEqualTo GetHistoricalData.Result.Success(
            historicalData = historicalData
        )
    }

    @Test
    fun `should return ResultError when GetHistoricalData returns Error`() = runTest {
        // given
        val message = "Please contact us!"
        coEvery { apiRepository.getHistoricalData() } returns ResultWrapper.Error(message)

        // when
        val result = getHistoricalData()

        // then
        result shouldBeEqualTo GetHistoricalData.Result.ErrorResponse(message)
    }

    @Test
    fun `should return ResultNetworkError when GetHistoricalData returns NetworkError`() = runTest {
        // given
        coEvery { apiRepository.getHistoricalData() } returns ResultWrapper.NetworkError

        // when
        val result = getHistoricalData()

        // then
        result shouldBeEqualTo GetHistoricalData.Result.NetworkError
    }
}