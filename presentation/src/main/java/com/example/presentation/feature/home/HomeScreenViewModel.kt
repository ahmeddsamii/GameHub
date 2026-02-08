package com.example.presentation.feature.home

import com.example.domain.repository.GameRepository
import com.example.presentation.shared.base.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeScreenViewModel(
    private val gamesRepo: GameRepository
): BaseViewModel<HomeUiState, HomeEffect>(
    initialState = HomeUiState()
), HomeInteractionListener {

    init {
        getGames()
    }

    private fun getGames(){
        tryToExecute(
            block = {gamesRepo.getGames()},
            onSuccess = {updateState{copy(games = it)}}
        )
    }

    override fun onClickGame(gameId: Int) {
        sendEffect(HomeEffect.NavigateToGameDetails(gameId))
    }
}