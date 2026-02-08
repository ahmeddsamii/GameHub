package com.example.domain.source

import com.example.domain.entity.Game

interface RemoteDataSource {
    suspend fun getGames(pageNumber: Int): List<Game>
}