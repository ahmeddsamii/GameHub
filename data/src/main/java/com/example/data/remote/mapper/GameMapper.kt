package com.example.data.remote.mapper

import com.example.data.remote.dto.GameDto
import com.example.data.utils.orZero
import com.example.domain.entity.Game

fun GameDto.toDomain(): Game {
    return Game(
        id = id.orZero(),
        name = name.orEmpty(),
        imageUrl = backgroundImage.orEmpty(),
        rating = rating.orZero()
    )
}