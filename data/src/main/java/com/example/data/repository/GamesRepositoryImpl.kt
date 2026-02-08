package com.example.data.repository

import com.example.data.remote.dto.GameDto
import com.example.data.remote.dto.PaginationResponse
import com.example.data.remote.mapper.toDomain
import com.example.data.utils.safeApiCall
import com.example.domain.entity.Game
import com.example.domain.repository.GameRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.koin.core.annotation.Single

@Single(binds = [GameRepository::class])
class GamesRepositoryImpl(
    private val httpClient: HttpClient
): GameRepository {
    override suspend fun getGames(pageNumber: Int): List<Game> {
        val response = safeApiCall<PaginationResponse<GameDto>> {
            httpClient.get("games") {
                parameter("page", pageNumber)
            }.body()
        }
        return response.results.map { it.toDomain() }
    }
}