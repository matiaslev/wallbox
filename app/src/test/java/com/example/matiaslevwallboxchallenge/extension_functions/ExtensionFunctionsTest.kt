package com.example.matiaslevwallboxchallenge.extension_functions

import com.example.domain.mock.MockDomainData
import com.example.domain.models.QuasarAction
import com.example.matiaslevwallboxchallenge.ui.widgets.getPieChartPowerValues
import com.example.matiaslevwallboxchallenge.utils.shouldBeEqualTo
import org.junit.Test


class ExtensionFunctionsTest {

    @Test
    fun getPieChartPowerValues_not_shouldAddValues_whenAreZero() {
        val liveData = MockDomainData.liveDataMock(
            absoluteQuasar = 0.00,
            quasarAction = QuasarAction.Nothing,
            solarPower = 0.00,
            gridPower = 0.00
        )

        liveData.getPieChartPowerValues() shouldBeEqualTo emptyList()
    }

    @Test
    fun getPieChartPowerValues_shouldAddValues_whenArePositive() {
        val liveData = MockDomainData.liveDataMock()

        liveData.getPieChartPowerValues().count() shouldBeEqualTo 3
    }

}