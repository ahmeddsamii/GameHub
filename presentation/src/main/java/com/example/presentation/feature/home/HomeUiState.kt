package com.example.presentation.feature.home

import com.example.domain.entity.Game
import com.example.presentation.shared.base.ErrorState

data class HomeUiState(
    val isLoading: Boolean = false,
    val games: List<Game> = emptyList(),
    val error: ErrorState? = null
)
