package com.example.domain.usecase

import com.example.domain.repository.GameRepository
import org.koin.core.annotation.Factory

@Factory
class GetGameDetailsUseCase(
    private val gameRepository: GameRepository
) {
    suspend operator fun invoke(id: Int) = gameRepository.getGameDetailsById(id)
}