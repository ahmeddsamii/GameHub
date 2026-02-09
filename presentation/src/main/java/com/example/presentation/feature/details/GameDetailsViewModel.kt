package com.example.presentation.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.example.domain.usecase.GetGameDetailsUseCase
import com.example.presentation.navigation.Route
import com.example.presentation.shared.base.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class GameDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val gameDetailsUseCase: GetGameDetailsUseCase
) : BaseViewModel<GameDetailsUiState, GameDetailsEffect>(GameDetailsUiState()),
    GameDetailsInteractionListener {

    val gameId = savedStateHandle.toRoute<Route.GameDetails>().gameId

    init {
        getGameDetailsById(gameId)
    }

    private fun getGameDetailsById(id: Int) {
        tryToExecute(
            block = { gameDetailsUseCase(id) },
            onSuccess = { game ->
                updateState { copy(gameDetails = game) }
            },
            onError = {
                updateState { copy(error = it) }
            },
            onEnd = { updateState { copy(isLoading = false) } }
        )
    }

    override fun onClickBack() {
        sendEffect(GameDetailsEffect.NavigateBack)
    }

    override fun onClickRetry() {
        updateState { copy(error = null) }
        getGameDetailsById(gameId)
    }
}