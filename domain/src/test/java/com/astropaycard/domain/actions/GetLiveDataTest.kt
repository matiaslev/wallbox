package com.astropaycard.domain.actions

import com.astropaycard.domain.base.shouldBeEqualTo
import com.example.domain.actions.GetLiveData
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
class GetLiveDataTest {
    private lateinit var getLiveData: GetLiveData

    @MockK
    internal lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getLiveData = GetLiveData(apiRepository)
    }

    @Test
    fun `should return ResultSuccess when GetLiveData returns Success`() = runTest {
        // given
        val liveData = MockDomainData.liveDataMock()
        coEvery { apiRepository.getLiveData() } returns ResultWrapper.Success(liveData)

        // when
        val result = getLiveData()

        // then
        result shouldBeEqualTo GetLiveData.Result.Success(liveData = liveData)
    }

    @Test
    fun `should return ResultError when GetLiveData returns Error`() = runTest {
        // given
        val message = "please contact us!"
        coEvery { apiRepository.getLiveData() } returns ResultWrapper.Error(message)

        // when
        val result = getLiveData()

        // then
        result shouldBeEqualTo GetLiveData.Result.ErrorResponse(message)
    }

    @Test
    fun `should return ResultNetworkError when GetLiveData returns NetworkError`() = runTest {
        // given
        coEvery { apiRepository.getLiveData() } returns ResultWrapper.NetworkError

        // when
        val result = getLiveData()

        // then
        result shouldBeEqualTo GetLiveData.Result.NetworkError
    }
}