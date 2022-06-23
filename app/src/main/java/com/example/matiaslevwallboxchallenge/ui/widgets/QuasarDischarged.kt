package com.example.matiaslevwallboxchallenge.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme

@Composable
fun QuasarDischarged(
    power: Double
) {
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.primary)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (power < 0) {
            Text(
                modifier = Modifier,
                text = Utils.decimalFormatOnlyShowDecimalIfNotZero.format(power * -1),
                style = MaterialTheme.typography.h4
            )
        } else {
            Text(
                text = stringResource(id = R.string.not_providing_building),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun Preview() {
    MatiasLevWallboxChallengeTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            QuasarDischarged(power = 38.732)
            Spacer(modifier = Modifier.height(16.dp))
            QuasarDischarged(power = -38.732)
        }
    }
}