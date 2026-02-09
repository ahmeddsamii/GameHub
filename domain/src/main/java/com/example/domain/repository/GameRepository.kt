package com.example.domain.repository

import com.example.domain.entity.Game
import com.example.domain.entity.GameDetails
import com.example.domain.entity.Genre
import org.koin.core.annotation.Single

@Single(binds = [GameRepository::class])
interface GameRepository {
    suspend fun getGames(pageNumber: Int, genres: String? = null): List<Game>
    suspend fun getGenres(pageNumber: Int): List<Genre>
    suspend fun getGameDetailsById(id: Int): GameDetails
}