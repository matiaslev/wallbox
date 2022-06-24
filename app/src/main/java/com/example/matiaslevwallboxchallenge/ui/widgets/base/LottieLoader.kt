package com.example.matiaslevwallboxchallenge.ui.widgets.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

enum class LottieResources(val file: String) {
    Loading("loading.json"),
    Error("error.json"),
    NetworkError("network_error.json"),
    Empty("empty.json")
}

@Composable
fun LottieLoader(
    modifier: Modifier,
    lottieResource: LottieResources
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(lottieResource.file))
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}