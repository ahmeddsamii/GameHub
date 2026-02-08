package com.example.presentation.feature.home

import androidx.lifecycle.viewModelScope
import com.example.domain.repository.GameRepository
import com.example.presentation.shared.base.BaseViewModel
import com.example.presentation.shared.base.createPager
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
            block = {
                createPager(
                    scope = viewModelScope,
                    pageSize = 20,
                    loadPage = { page -> gamesRepo.getGames(page) }
                )
            },
            onSuccess = {updateState{copy(games = it)}}
        )
    }

    override fun onClickGame(gameId: Int) {
        sendEffect(HomeEffect.NavigateToGameDetails(gameId))
    }
}