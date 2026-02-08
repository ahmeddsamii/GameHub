package com.example.presentation.feature.home

sealed interface HomeEffect {
    data class NavigateToGameDetails(val gameId: Int): HomeEffect
}