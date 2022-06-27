package com.example.matiaslevwallboxchallenge.ui.widgets

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.TextView
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.screens.historical_data.epochToformattedLineChartDateTime
import com.example.matiaslevwallboxchallenge.ui.screens.historical_data.getChartDateFormat
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight


class HistoricalDataMarker(
    context: Context,
    attrs: AttributeSet? = null,
    layoutResource: Int
) : MarkerView(context, layoutResource) {

    private val uiScreenWidth = resources.displayMetrics.widthPixels

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        e?.let { entry ->
            findViewById<TextView>(R.id.dateTime).apply {
                text =  getChartDateFormat(entry.x.epochToformattedLineChartDateTime())
            }

            findViewById<TextView>(R.id.kw).apply {
                text = context.getString(
                    R.string.kw_value,
                    Utils.decimalFormatOnlyShowDecimalIfNotZero.format(entry.y)
                )
            }
        }
        super.refreshContent(e, highlight)
    }

    override fun draw(canvas: Canvas?, posX: Float, posY: Float) {
        var newPosX = posX
        if (uiScreenWidth - posX < width) {
            newPosX -= width
        }
        super.draw(canvas, newPosX, posY)
    }
}