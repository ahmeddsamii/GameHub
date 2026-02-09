package com.example.data.local.mapper

import com.example.data.local.entity.GameEntity
import com.example.domain.entity.Game

fun GameEntity.toDomain(): Game {
    return Game(
        id = id,
        name = name,
        imageUrl = imageUrl,
        rating = rating
    )
}

fun Game.toEntity(): GameEntity {
    return GameEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        rating = rating
    )
}

fun List<GameEntity>.toDomain(): List<Game> {
    return this.map { it.toDomain() }
}

fun List<Game>.toEntity(): List<GameEntity> {
    return this.map { it.toEntity() }
}