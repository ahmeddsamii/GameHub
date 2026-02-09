package com.example.data.local.mapper

import com.example.data.local.entity.GameDetailsEntity
import com.example.domain.entity.GameDetails

fun GameDetailsEntity.toDomain(): GameDetails {
    return GameDetails(
        name = name,
        backgroundImageUrl = backgroundImageUrl,
        releaseDate = releaseDate,
        rating = rating,
        description = description
    )
}

fun GameDetails.toEntity(id: Int): GameDetailsEntity {
    return GameDetailsEntity(
        id = id,
        name = name,
        backgroundImageUrl = backgroundImageUrl,
        releaseDate = releaseDate,
        rating = rating,
        description = description
    )
}