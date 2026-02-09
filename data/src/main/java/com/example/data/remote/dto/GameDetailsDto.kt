package com.example.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailsDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("name_original")
    val nameOriginal: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("metacritic")
    val metacritic: Int? = null,
    @SerialName("released")
    val released: String? = null,
    @SerialName("background_image")
    val backgroundImage: String? = null,
    @SerialName("rating")
    val rating: Double? = null,
)
