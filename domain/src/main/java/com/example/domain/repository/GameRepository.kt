package com.example.domain.repository

import com.example.domain.entity.Game
import org.koin.core.annotation.Single

@Single(binds = [GameRepository::class])
interface GameRepository {
    suspend fun getGames(pageNumber: Int): List<Game>
}