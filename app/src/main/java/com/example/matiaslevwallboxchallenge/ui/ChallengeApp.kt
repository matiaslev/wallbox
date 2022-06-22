package com.example.matiaslevwallboxchallenge.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matiaslevwallboxchallenge.ui.screens.HistoricalDataScreen
import com.example.matiaslevwallboxchallenge.ui.screens.LiveDataScreen
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
                startDestination =  "LiveData"
            ) {
                composable(
                    route = "LiveData"
                ) {
                    LiveDataScreen(
                        navController = navController
                    )
                }
                composable(
                    route = "HistoricalData"
                ) {
                    HistoricalDataScreen()
                }
            }
        }
    }
}