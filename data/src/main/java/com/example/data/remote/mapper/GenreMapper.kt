package com.example.data.remote.mapper

import com.example.data.remote.dto.GenreDto
import com.example.data.utils.orZero
import com.example.domain.entity.Genre

fun GenreDto.toDomain(): Genre{
    return Genre(
        id = id.orZero(),
        name = name.orEmpty(),
        slug = slug.orEmpty()
    )
}