package com.example.matiaslevwallboxchallenge

import com.example.domain.actions.GetLiveData
import com.example.domain.logger.Logger
import com.example.domain.mock.MockDomainData
import com.example.matiaslevwallboxchallenge.ui.screens.live_data.LiveDataViewModel
import com.example.matiaslevwallboxchallenge.ui.widgets.base.ViewStateType
import com.example.matiaslevwallboxchallenge.utils.BaseTest
import com.example.matiaslevwallboxchallenge.utils.shouldBeEqualTo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LiveDataViewModelTest : BaseTest() {

    private lateinit var viewModel: LiveDataViewModel

    @MockK(relaxed = true)
    lateinit var getLiveData: GetLiveData

    @MockK(relaxed = true)
    lateinit var logger: Logger

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = LiveDataViewModel(getLiveData, logger)
    }

    @Test
    fun `should return ResultSuccess when confirmCardDeposit returns Success`() = runTest {
        // given
        val liveData = MockDomainData.liveDataMock()
        coEvery { getLiveData() } returns GetLiveData.Result.Success(liveData)

        // when
        viewModel.loadData()

        // then
        viewModel.state shouldBeEqualTo LiveDataViewModel.ViewState(
            viewStateType = ViewStateType.Success,
            liveData = liveData
        )
    }

}