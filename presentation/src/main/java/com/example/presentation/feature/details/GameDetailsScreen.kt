package com.example.presentation.feature.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameDetailsScreen(
    viewModel: GameDetailsViewModel = koinViewModel()
) {

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Details Page with id: ${viewModel.getGameId()}")
    }
}