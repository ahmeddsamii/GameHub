package com.example.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("games_count")
    val gameCount: Int? = null,
    @SerialName("image_background")
    val backgroundImageUrl: String? = null
)
