package com.example.data.remote.mapper

import com.example.data.remote.dto.GameDetailsDto
import com.example.data.utils.orZero
import com.example.domain.entity.GameDetails

fun GameDetailsDto.toDomain(): GameDetails {
    return GameDetails(
        name = name.orEmpty(),
        backgroundImageUrl = backgroundImage.orEmpty(),
        releaseDate = released.orEmpty(),
        rating = rating.orZero(),
        description = description.orEmpty()
    )
}