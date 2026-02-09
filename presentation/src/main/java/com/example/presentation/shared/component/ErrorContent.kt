package com.example.presentation.shared.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.shared.base.ErrorState

@Composable
fun ErrorContent(error: ErrorState?, onRetryClick: () -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = when (error) {
                    ErrorState.NoInternet -> "No Internet Connection"
                    ErrorState.NotFound -> "Content Not Found"
                    ErrorState.RequestTimeout -> "Request Timeout"
                    is ErrorState.RequestFailed -> "Request Failed"
                    else -> "Something went wrong"
                },
                style = MaterialTheme.typography.bodyLarge
            )

            Button(onClick = onRetryClick) {
                Text("Retry")
            }
        }
    }
}