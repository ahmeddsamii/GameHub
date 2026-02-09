package com.example.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.feature.details.GameDetailsScreen
import com.example.presentation.feature.home.HomeScreen

@Composable
fun MainNavHost() {
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalNavController provides navController,
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->

            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                navController = navController,
                startDestination = Route.Home,
            ) {
                composable<Route.Home> {
                    HomeScreen()
                }

                composable<Route.GameDetails> {
                    GameDetailsScreen()
                }
            }
        }
    }
}
val LocalNavController = compositionLocalOf<NavController> {
    error("NavController not provided")
}