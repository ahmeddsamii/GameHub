package com.example.data.local.source

import com.example.data.local.dao.GameDao
import com.example.data.local.dao.GameDetailsDao
import com.example.data.local.dao.GenreDao
import com.example.data.local.mapper.toDomain
import com.example.data.local.mapper.toEntity
import com.example.domain.entity.Game
import com.example.domain.entity.GameDetails
import com.example.domain.entity.Genre
import com.example.domain.source.LocalDataSource
import org.koin.core.annotation.Single

@Single(binds = [LocalDataSource::class])
class LocalDataSourceImpl(
    private val gameDao: GameDao,
    private val genreDao: GenreDao,
    private val gameDetailsDao: GameDetailsDao
) : LocalDataSource {

    override suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames().toDomain()
    }

    override suspend fun insertGames(games: List<Game>) {
        gameDao.insertGames(games.toEntity())
    }

    override suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

    override suspend fun getAllGenres(): List<Genre> {
        return genreDao.getAllGenres().toDomain()
    }

    override suspend fun insertGenres(genres: List<Genre>) {
        genreDao.insertGenres(genres.toEntity())
    }

    override suspend fun getGameDetailsById(id: Int): GameDetails? {
        return gameDetailsDao.getGameDetailsById(id)?.toDomain()
    }

    override suspend fun insertGameDetails(id: Int, gameDetails: GameDetails) {
        gameDetailsDao.insertGameDetails(gameDetails.toEntity(id))
    }
}
