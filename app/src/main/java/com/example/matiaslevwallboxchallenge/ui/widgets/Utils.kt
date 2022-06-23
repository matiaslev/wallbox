package com.example.matiaslevwallboxchallenge.ui.widgets

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object Utils {

    private var nf: DecimalFormat = DecimalFormat("#.##", DecimalFormatSymbols())
    val decimalFormatOnlyShowDecimalIfNotZero: DecimalFormat
        get() {
            nf.isDecimalSeparatorAlwaysShown = false
            nf.roundingMode = RoundingMode.DOWN
            return nf
        }
}