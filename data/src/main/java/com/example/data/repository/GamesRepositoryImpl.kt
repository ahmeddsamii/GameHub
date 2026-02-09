package com.example.data.repository

import com.example.domain.entity.Game
import com.example.domain.entity.GameDetails
import com.example.domain.entity.Genre
import com.example.domain.repository.GameRepository
import com.example.domain.source.LocalDataSource
import com.example.domain.source.RemoteDataSource
import org.koin.core.annotation.Single

@Single(binds = [GameRepository::class])
class GamesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : GameRepository {

    override suspend fun getGames(pageNumber: Int, genres: String?): List<Game> {
        return try {
            val remoteGames = remoteDataSource.getGames(pageNumber, genres)

            if (pageNumber == 1) localDataSource.deleteAllGames()
            localDataSource.insertGames(remoteGames)

            remoteGames
        } catch (e: Exception) {
            val localGames = localDataSource.getAllGames()
            localGames.ifEmpty { throw e }
        }
    }

    override suspend fun getGameDetailsById(id: Int): GameDetails {
        return try {
            val remoteDetails = remoteDataSource.getGameDetailsById(id)
            localDataSource.insertGameDetails(id, remoteDetails)
            remoteDetails
        } catch (e: Exception) {
            localDataSource.getGameDetailsById(id) ?: throw e
        }
    }

    override suspend fun getGenres(pageNumber: Int): List<Genre> {
        return try {
            val remoteGenres = remoteDataSource.getGenres(pageNumber)
            localDataSource.insertGenres(remoteGenres)
            remoteGenres
        } catch (e: Exception) {
            localDataSource.getAllGenres().ifEmpty { throw e }
        }
    }
}