package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.database.GamesDatabase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.example.data")
class DataModule{
    @Single
    fun provideGamesDatabase(context: Context): GamesDatabase {
        return Room.databaseBuilder(
            context,
            GamesDatabase::class.java,
            "games_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Single
    fun provideGameDao(database: GamesDatabase) = database.gameDao()

    @Single
    fun provideGenreDao(database: GamesDatabase) = database.genreDao()

    @Single
    fun provideGameDetailsDao(database: GamesDatabase) = database.gameDetailsDao()
}