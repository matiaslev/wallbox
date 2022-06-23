package com.astropaycard.domain.actions

import com.astropaycard.domain.base.shouldBeEqualTo
import com.example.domain.actions.GetHistoricalData
import com.example.domain.base.ResultWrapper
import com.example.domain.mock.MockDomainData
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
        val historicalData = MockDomainData.historicalDataMock()
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