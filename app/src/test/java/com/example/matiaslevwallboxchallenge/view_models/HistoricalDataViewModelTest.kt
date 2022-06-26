package com.example.matiaslevwallboxchallenge.view_models

import com.example.domain.actions.GetHistoricalData
import com.example.domain.logger.Logger
import com.example.domain.mock.MockDomainData
import com.example.matiaslevwallboxchallenge.ui.screens.historical_data.HistoricalDataViewModel
import com.example.matiaslevwallboxchallenge.ui.widgets.base.ViewStateType
import com.example.matiaslevwallboxchallenge.utils.BaseTest
import com.example.matiaslevwallboxchallenge.utils.shouldBeEqualTo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HistoricalDataViewModelTest : BaseTest() {
    private lateinit var viewModel: HistoricalDataViewModel

    @MockK(relaxed = true)
    lateinit var getHistoricalData: GetHistoricalData

    @MockK(relaxed = true)
    lateinit var logger: Logger

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = HistoricalDataViewModel(getHistoricalData, logger)
    }

    @Test
    fun `should return ResultSuccess when GetHistoricalData returns Success`() = runTest {
        // given
        val historicalData = MockDomainData.historicalDataMock()
        coEvery { getHistoricalData() } returns GetHistoricalData.Result.Success(historicalData)

        // when
        viewModel.loadData()

        // then
        viewModel.state shouldBeEqualTo HistoricalDataViewModel.ViewState(
            viewStateType = ViewStateType.Success,
            historicalData = historicalData
        )
    }

    @Test
    fun `should return ResultSuccess when GetHistoricalData returns Error`() = runTest {
        // given
        val message = "error message"
        coEvery { getHistoricalData() } returns GetHistoricalData.Result.ErrorResponse(message)

        // when
        viewModel.loadData()

        // then
        viewModel.state shouldBeEqualTo HistoricalDataViewModel.ViewState(
            viewStateType = ViewStateType.Error(message),
            historicalData = null
        )
    }

    @Test
    fun `should return ResultSuccess when GetHistoricalData returns NetworkError`() = runTest {
        // given
        coEvery { getHistoricalData() } returns GetHistoricalData.Result.NetworkError

        // when
        viewModel.loadData()

        // then
        viewModel.state shouldBeEqualTo HistoricalDataViewModel.ViewState(
            viewStateType = ViewStateType.NetworkError,
            historicalData = null
        )
    }
}