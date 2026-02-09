package com.example.presentation.navigation

import kotlinx.serialization.Serializable


sealed interface Route {
    @Serializable
    data object Home : Route

    @Serializable
    data class GameDetails(val gameId: Int) : Route
}