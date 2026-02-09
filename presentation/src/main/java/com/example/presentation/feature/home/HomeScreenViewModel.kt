package com.example.presentation.feature.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import com.example.domain.entity.Game
import com.example.domain.usecase.GetAllGamesUseCase
import com.example.domain.usecase.GetAllGenresUseCase
import com.example.presentation.shared.base.BaseViewModel
import com.example.presentation.shared.base.createPager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeScreenViewModel(
    private val getAllGamesUseCase: GetAllGamesUseCase,
    private val getAllGenresUseCase: GetAllGenresUseCase,
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
                    loadPage = { page -> getAllGenresUseCase(page) }
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
                    loadPage = { page -> getAllGamesUseCase(page, genres) }
                )
            },
            onSuccess = { pager ->
                onGetGamesSuccess(pager)
            }
        )
    }

    private fun onGetGamesSuccess(pager: Flow<PagingData<Game>>) {
        updateState {
            copy(
                games = pager,
                filteredGames = pager.map { list ->
                    if (query.isBlank()) list
                    else list.filter { it.name.contains(query, ignoreCase = true) }
                }
            )
        }
    }

    override fun onSearch(query: String) {
        updateState { copy(query = query) }

        updateState {
            copy(
                filteredGames = state.value.games.map { list ->
                    if (query.isBlank()) list
                    else list.filter { it.name.contains(query, ignoreCase = true) }
                }
            )
        }
    }

    override fun onClickGame(gameId: Int) {
        sendEffect(HomeEffect.NavigateToGameDetails(gameId))
    }

    override fun onClickGenre(slug: String) {
        getGames(genres = slug)
    }

    override fun onClickRetry() {
        updateState { copy(error = null) }
        getGames()
        getGenres()
    }
}