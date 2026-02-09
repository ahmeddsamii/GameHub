package com.example.presentation.feature.home

import androidx.paging.PagingData
import com.example.domain.entity.Game
import com.example.domain.entity.Genre
import com.example.presentation.shared.base.ErrorState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeUiState(
    val isLoading: Boolean = false,
    val games: Flow<PagingData<Game>> = emptyFlow(),
    val genres: Flow<PagingData<Genre>> = emptyFlow(),
    val error: ErrorState? = null
)
