package com.example.domain.usecase

import com.example.domain.repository.GameRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class GetAllGamesUseCaseTest {

    private val repository: GameRepository = mockk()
    private val useCase = GetAllGamesUseCase(repository)

    @Test
    fun `invoke should call repository getGames`() = runBlocking {
        coEvery { repository.getGames(any(), any()) } returns emptyList()

        useCase(pageNumber = 1, genres = "RPG")

        coVerify(exactly = 1) { repository.getGames(1, "RPG") }
    }
}