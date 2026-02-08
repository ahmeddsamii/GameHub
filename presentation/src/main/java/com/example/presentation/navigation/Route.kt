package com.example.presentation.navigation

import kotlinx.serialization.Serializable


sealed interface Route {
    @Serializable
    data object Home : Route
}