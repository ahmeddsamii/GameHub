package com.example.presentation.feature.details

import com.example.domain.entity.GameDetails
import com.example.presentation.shared.base.ErrorState

data class GameDetailsUiState(
    val isLoading: Boolean = true,
    val error: ErrorState? = null,
    val gameDetails: GameDetails? = null,
)