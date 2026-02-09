package com.example.data.remote.source

import com.example.data.remote.dto.GameDetailsDto
import com.example.data.remote.dto.GameDto
import com.example.data.remote.dto.GenreDto
import com.example.data.remote.dto.PaginationResponse
import com.example.data.remote.mapper.toDomain
import com.example.data.utils.safeApiCall
import com.example.domain.entity.Game
import com.example.domain.entity.GameDetails
import com.example.domain.entity.Genre
import com.example.domain.source.RemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.koin.core.annotation.Single

@Single(binds = [RemoteDataSource::class])
class RemoteDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteDataSource {
    override suspend fun getGames(
        pageNumber: Int,
        genres: String?
    ): List<Game> {
        val response = safeApiCall<PaginationResponse<GameDto>> {
            httpClient.get("games") {
                parameter("page", pageNumber)
                parameter("genres", genres)
            }.body()
        }
        return response.results.map { it.toDomain() }
    }

    override suspend fun getGenres(
        pageNumber: Int,
    ): List<Genre> {
        val response = safeApiCall<PaginationResponse<GenreDto>> {
            httpClient.get("genres") {
                parameter("page", pageNumber)
            }.body()
        }
        return response.results.map { it.toDomain() }
    }

    override suspend fun getGameDetailsById(id: Int): GameDetails {
        val response = safeApiCall<GameDetailsDto> {
            httpClient.get("games/$id").body()
        }
        return response.toDomain()
    }
}