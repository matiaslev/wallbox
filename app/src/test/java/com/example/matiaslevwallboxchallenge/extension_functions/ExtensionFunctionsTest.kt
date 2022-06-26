package com.example.matiaslevwallboxchallenge.extension_functions

import android.content.Context
import com.example.domain.mock.MockDomainData
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.ui.widgets.getPieChartPowerValues
import com.example.matiaslevwallboxchallenge.utils.shouldBeEqualTo
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test


class ExtensionFunctionsTest {

    @MockK(relaxed = true)
    lateinit var context: Context

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getPieChartPowerValues_not_shouldAddValues_whenAreZero() {
        val liveData = MockDomainData.liveDataMock(
            absoluteQuasar = 0.00,
            quasarAction = QuasarAction.Nothing,
            solarPower = 0.00,
            gridPower = 0.00
        )

        liveData.getPieChartPowerValues(context) shouldBeEqualTo emptyList()
    }

    @Test
    fun getPieChartPowerValues_shouldAddValues_whenArePositive() {
        val liveData = MockDomainData.liveDataMock()

        liveData.getPieChartPowerValues(context).count() shouldBeEqualTo 3
    }

}