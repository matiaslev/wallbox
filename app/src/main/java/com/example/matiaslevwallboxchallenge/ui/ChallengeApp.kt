package com.example.matiaslevwallboxchallenge.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matiaslevwallboxchallenge.ui.screens.historical_data.HistoricalDataScreen
import com.example.matiaslevwallboxchallenge.ui.screens.live_data.LiveDataScreen
import com.example.matiaslevwallboxchallenge.ui.theme.MatiasLevWallboxChallengeTheme

@Composable
fun ChallengeApp() {
    MatiasLevWallboxChallengeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination =  Screens.LiveData.name
            ) {
                composable(
                    route = Screens.LiveData.name
                ) {
                    LiveDataScreen(
                        navController = navController
                    )
                }
                composable(
                    route = Screens.HistoricalData.name
                ) {
                    HistoricalDataScreen(
                        navController = navController
                    )
                }
            }
        }
    }
}

enum class Screens {
    LiveData, HistoricalData
}