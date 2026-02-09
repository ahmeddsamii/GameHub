package com.example.data.local.mapper

import com.example.data.local.entity.GenreEntity
import com.example.domain.entity.Genre

fun GenreEntity.toDomain(): Genre {
    return Genre(
        id = id,
        name = name,
        slug = slug
    )
}

fun Genre.toEntity(): GenreEntity {
    return GenreEntity(
        id = id,
        name = name,
        slug = slug
    )
}

fun List<GenreEntity>.toDomain(): List<Genre> {
    return this.map { it.toDomain() }
}

fun List<Genre>.toEntity(): List<GenreEntity> {
    return this.map { it.toEntity() }
}