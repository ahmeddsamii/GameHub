package com.example.data.repository

import com.example.domain.entity.Game
import com.example.domain.repository.GameRepository
import com.example.domain.source.RemoteDataSource
import org.koin.core.annotation.Single

@Single(binds = [GameRepository::class])
class GamesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): GameRepository {
    override suspend fun getGames(pageNumber: Int): List<Game> {
        return remoteDataSource.getGames(pageNumber)
    }
}