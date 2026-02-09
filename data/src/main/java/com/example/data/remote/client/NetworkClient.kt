package com.example.data.remote.client

import com.example.data.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single

@Single
fun provideHttpClient(): HttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            coerceInputValues = true
        })
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                android.util.Log.d("KTOR_LOG", message)
            }
        }
        level = LogLevel.ALL
    }

    defaultRequest {
        url("https://api.rawg.io/api/")
        url {
            parameters.append("key", BuildConfig.API_KEY)
        }
    }
}
