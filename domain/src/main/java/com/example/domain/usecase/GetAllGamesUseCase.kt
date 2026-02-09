package com.example.domain.usecase

import com.example.domain.repository.GameRepository
import org.koin.core.annotation.Factory

@Factory
class GetAllGamesUseCase(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(pageNumber: Int, genres: String? = null) =
        gameRepository.getGames(pageNumber = pageNumber, genres = genres)
}