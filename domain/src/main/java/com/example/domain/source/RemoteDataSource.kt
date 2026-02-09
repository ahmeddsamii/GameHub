package com.example.domain.source

import com.example.domain.entity.Game
import com.example.domain.entity.Genre

interface RemoteDataSource {
    suspend fun getGames(pageNumber: Int, genres: String? = null): List<Game>
    suspend fun getGenres(pageNumber: Int): List<Genre>
}