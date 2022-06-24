package com.example.matiaslevwallboxchallenge.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.matiaslevwallboxchallenge.ui.base.LastIntention
import com.example.matiaslevwallboxchallenge.ui.widgets.base.LottieLoader
import com.example.matiaslevwallboxchallenge.ui.widgets.base.LottieResources

sealed class ViewStateType {
    object Start : ViewStateType()
    object Success: ViewStateType()
    object Loading: ViewStateType()
    object Empty: ViewStateType()
    object NetworkError: ViewStateType()
    data class Error(val message: String): ViewStateType()
}

@Composable
fun ContentState(
    state: ViewStateType,
    lastIntention: LastIntention,
    content: @Composable () -> Unit
) {
    when(state) {
        ViewStateType.Success -> content()
        ViewStateType.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LottieLoader(
                    modifier = Modifier.size(120.dp),
                    lottieResource = LottieResources.Loading
                )
            }
        }
        ViewStateType.Empty -> TODO()
        is ViewStateType.Error -> TODO()
        ViewStateType.NetworkError -> TODO()
        ViewStateType.Start -> {
            // Nothing to do here, just wait
        }
    }
}