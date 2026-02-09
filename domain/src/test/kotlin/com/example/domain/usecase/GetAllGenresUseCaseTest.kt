package com.example.domain.usecase

import com.example.domain.repository.GameRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class GetAllGenresUseCaseTest {

    private val repository: GameRepository = mockk()
    private val useCase = GetAllGenresUseCase(repository)

    @Test
    fun `invoke calls repository getGenres with correct page`() = runBlocking {
        // Arrange
        val page = 2
        coEvery { repository.getGenres(page) } returns emptyList()

        // Act
        useCase(page)

        // Assert
        coVerify(exactly = 1) { repository.getGenres(page) }
    }
}