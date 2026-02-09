package com.example.presentation.feature.details

sealed interface GameDetailsEffect {
    object NavigateBack : GameDetailsEffect
}