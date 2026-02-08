package com.example.gamehub.di

import android.app.Application
import com.example.data.di.DataModule
import com.example.presentation.di.PresentationModule
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class GameHubApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(DataModule().module, PresentationModule().module)
        }
    }
}