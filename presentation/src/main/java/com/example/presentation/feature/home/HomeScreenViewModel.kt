package com.example.presentation.feature.home

import androidx.lifecycle.viewModelScope
import com.example.domain.repository.GameRepository
import com.example.presentation.shared.base.BaseViewModel
import com.example.presentation.shared.base.createPager
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeScreenViewModel(
    private val gamesRepo: GameRepository
) : BaseViewModel<HomeUiState, HomeEffect>(
    initialState = HomeUiState()
), HomeInteractionListener {

    init {
        getGames()
        getGenres()
    }

    private fun getGenres() {
        tryToExecute(
            block = {
                createPager(
                    scope = viewModelScope,
                    loadPage = { page -> gamesRepo.getGenres(page) }
                )
            },
            onSuccess = { updateState { copy(genres = it) } }
        )
    }

    private fun getGames(genres: String? = null) {
        tryToExecute(
            block = {
                createPager(
                    scope = viewModelScope,
                    loadPage = { page -> gamesRepo.getGames(page, genres) }
                )
            },
            onSuccess = { updateState { copy(games = it) } }
        )
    }

    override fun onClickGame(gameId: Int) {
        sendEffect(HomeEffect.NavigateToGameDetails(gameId))
    }

    override fun onClickGenre(slug: String) {
        getGames(genres = slug)
    }
}