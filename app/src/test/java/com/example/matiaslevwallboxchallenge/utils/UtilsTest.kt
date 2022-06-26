package com.example.matiaslevwallboxchallenge.utils

import com.example.matiaslevwallboxchallenge.helper.shouldBeEqualTo
import com.example.matiaslevwallboxchallenge.ui.widgets.Utils
import org.junit.Before
import org.junit.Test
import java.util.*


class UtilsTest {

    @Before
    fun setUp() {
        Locale.setDefault(Locale.US)
    }

    @Test
    fun utils_decimalFormatOnlyShowDecimalIfNotZero_test_case_1() {
        val formattedValue = Utils.decimalFormatOnlyShowDecimalIfNotZero.format(38.732)

        formattedValue shouldBeEqualTo "38.73"
    }

    @Test
    fun utils_decimalFormatOnlyShowDecimalIfNotZero_test_case_2() {
        val formattedValue = Utils.decimalFormatOnlyShowDecimalIfNotZero.format(7.827)

        formattedValue shouldBeEqualTo "7.83"
    }

    @Test
    fun utils_decimalFormatOnlyShowDecimalIfNotZero_test_case_3() {
        val formattedValue = Utils.decimalFormatOnlyShowDecimalIfNotZero.format(80.475)

        formattedValue shouldBeEqualTo "80.47"
    }

    @Test
    fun utils_decimalFormatOnlyShowDecimalIfNotZero_test_case_4() {
        val formattedValue = Utils.decimalFormatOnlyShowDecimalIfNotZero.format(80.4756)

        formattedValue shouldBeEqualTo "80.48"
    }

}