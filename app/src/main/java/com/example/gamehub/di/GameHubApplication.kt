package com.example.gamehub.di

import android.app.Application
import com.example.data.di.DataModule
import com.example.domain.di.DomainModule
import com.example.presentation.di.PresentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.ksp.generated.module

class GameHubApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                module {
                    single<android.content.Context> { this@GameHubApplication }
                },
                DataModule().module,
                DomainModule().module,
                PresentationModule().module
            )
        }
    }
}