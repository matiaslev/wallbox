package com.example.matiaslevwallboxchallenge.ui.widgets

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

object Utils {

    private var nf: DecimalFormat = DecimalFormat("#.##", DecimalFormatSymbols(Locale.getDefault()))
    val decimalFormatOnlyShowDecimalIfNotZero: DecimalFormat
        get() {
            nf.isDecimalSeparatorAlwaysShown = false
            nf.roundingMode = RoundingMode.HALF_DOWN
            return nf
        }
}