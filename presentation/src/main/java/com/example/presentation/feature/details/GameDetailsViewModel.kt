package com.example.presentation.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.example.presentation.navigation.Route
import com.example.presentation.shared.base.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class GameDetailsViewModel(
    savedStateHandle: SavedStateHandle,
): BaseViewModel<GameDetailsUiState, GameDetailsEffect>(GameDetailsUiState()) {
    val id = savedStateHandle.toRoute<Route.GameDetails>().gameId

    fun getGameId(): Int{
        return id
    }
}