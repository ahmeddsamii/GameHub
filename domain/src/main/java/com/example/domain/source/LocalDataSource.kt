package com.example.domain.source

import com.example.domain.entity.Game
import com.example.domain.entity.GameDetails
import com.example.domain.entity.Genre

interface LocalDataSource {
    suspend fun getAllGames(): List<Game>
    suspend fun insertGames(games: List<Game>)
    suspend fun deleteAllGames()

    suspend fun getAllGenres(): List<Genre>
    suspend fun insertGenres(genres: List<Genre>)

    suspend fun getGameDetailsById(id: Int): GameDetails?
    suspend fun insertGameDetails(id: Int, gameDetails: GameDetails)
}