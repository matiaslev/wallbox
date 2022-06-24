package com.example.matiaslevwallboxchallenge.ui.widgets.base

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matiaslevwallboxchallenge.R
import com.example.matiaslevwallboxchallenge.ui.base.LastIntention
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme
import com.example.matiaslevwallboxchallenge.ui.widgets.base.LottieLoader
import com.example.matiaslevwallboxchallenge.ui.widgets.base.LottieResources

sealed class ViewStateType {
    object Start : ViewStateType()
    object Success : ViewStateType()
    object Loading : ViewStateType()
    object Empty : ViewStateType()
    object NetworkError : ViewStateType()
    data class Error(val message: String) : ViewStateType()
}

@Composable
fun ContentState(
    state: ViewStateType,
    lastIntention: LastIntention,
    content: @Composable () -> Unit
) {
    when (state) {
        ViewStateType.Success -> content()
        ViewStateType.Loading -> LoadingState()
        ViewStateType.Empty -> EmptyState()
        is ViewStateType.Error -> ErrorState(
            lastIntention = lastIntention,
            message = state.message,

        )
        ViewStateType.NetworkError -> NetworkErrorState(
            lastIntention = lastIntention
        )
        ViewStateType.Start -> {
            // Nothing to do here, just wait
        }
    }
}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieLoader(
            modifier = Modifier
                .size(120.dp)
                .testTag(LottieResources.Loading.file),
            lottieResource = LottieResources.Loading
        )
    }
}

@Composable
private fun ErrorState(
    message: String,
    lastIntention: LastIntention,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieLoader(
            modifier = Modifier
                .size(120.dp)
                .testTag(LottieResources.Error.file),
            lottieResource = LottieResources.Error
        )

        Text(
            modifier = Modifier
                .padding(16.dp),
            text = message,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )

        lastIntention?.let { lastIntention ->
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = lastIntention
            ) {
                Text(text = stringResource(id = R.string.try_again))
            }
        }
    }
}

@Composable
private fun NetworkErrorState(
    lastIntention: LastIntention
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieLoader(
            modifier = Modifier
                .size(120.dp)
                .testTag(LottieResources.NetworkError.file),
            lottieResource = LottieResources.NetworkError
        )

        Text(
            modifier = Modifier
                .padding(16.dp),
            text = stringResource(id = R.string.network_error),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )

        lastIntention?.let { lastIntention ->
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = lastIntention
            ) {
                Text(text = stringResource(id = R.string.try_again))
            }
        }
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieLoader(
            modifier = Modifier
                .size(120.dp)
                .testTag(LottieResources.Empty.file),
            lottieResource = LottieResources.Empty
        )

        Text(
            modifier = Modifier
                .padding(16.dp),
            text = stringResource(id = R.string.we_not_have_data_to_show_you_yet),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Lottie preview is not working
 */

@Composable
@Preview(
    name = "Success",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Success",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun PreviewSuccess() {
    MatiasLevWallboxChallengeTheme {
        ContentState(state = ViewStateType.Success, lastIntention = null) {
            Text(text = "Content")
        }
    }
}

@Composable
@Preview(
    name = "Error",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Error",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun PreviewError() {
    MatiasLevWallboxChallengeTheme {
        ContentState(state = ViewStateType.Error("something went wrong"), lastIntention = null) {
            Text(text = "Content")
        }
    }
}

@Composable
@Preview(
    name = "NetworkError",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "NetworkError",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun PreviewNetworkError() {
    MatiasLevWallboxChallengeTheme {
        ContentState(state = ViewStateType.NetworkError, lastIntention = null) {
            Text(text = "Content")
        }
    }
}

@Composable
@Preview(
    name = "Empty",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Empty",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun PreviewEmpty() {
    MatiasLevWallboxChallengeTheme {
        ContentState(state = ViewStateType.Empty, lastIntention = null) {
            Text(text = "Content")
        }
    }
}
