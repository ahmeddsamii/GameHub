package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.GameDetailsEntity

@Dao
interface GameDetailsDao {
    @Query("SELECT * FROM game_details WHERE id = :id")
    suspend fun getGameDetailsById(id: Int): GameDetailsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameDetails(gameDetails: GameDetailsEntity)
}