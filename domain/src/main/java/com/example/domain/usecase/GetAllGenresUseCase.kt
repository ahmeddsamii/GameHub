package com.example.domain.usecase

import com.example.domain.repository.GameRepository
import org.koin.core.annotation.Factory

@Factory
class GetAllGenresUseCase(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(pageNumber: Int) = gameRepository.getGenres(pageNumber)
}