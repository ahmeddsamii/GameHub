package com.example.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_details")
data class GameDetailsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "background_image_url")
    val backgroundImageUrl: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis()
)