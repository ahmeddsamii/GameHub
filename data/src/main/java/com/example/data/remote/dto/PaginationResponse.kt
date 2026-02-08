package com.example.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationResponse<T> (
    @SerialName("count")
    val count: Int? = null,

    @SerialName("next")
    val next: String? = null,

    @SerialName("previous")
    val previous: String? = null,

    @SerialName("results")
    val results: List<T>
)

