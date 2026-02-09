package com.example.domain.usecase

import com.example.domain.entity.GameDetails
import com.example.domain.repository.GameRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GetGameDetailsUseCaseTest {

    private val repository: GameRepository = mockk()
    private val useCase = GetGameDetailsUseCase(repository)

    @Test
    fun `invoke returns game details from repository`() = runBlocking {
        val id = 123
        val mockDetails = mockk<GameDetails>()
        coEvery { repository.getGameDetailsById(id) } returns mockDetails

        val result = useCase(id)

        assertEquals(mockDetails, result)
    }
}