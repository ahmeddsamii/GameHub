package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.GameDao
import com.example.data.local.dao.GameDetailsDao
import com.example.data.local.dao.GenreDao
import com.example.data.local.entity.GameDetailsEntity
import com.example.data.local.entity.GameEntity
import com.example.data.local.entity.GenreEntity

@Database(
    entities = [
        GameEntity::class,
        GenreEntity::class,
        GameDetailsEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class GamesDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun genreDao(): GenreDao
    abstract fun gameDetailsDao(): GameDetailsDao
}