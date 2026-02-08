package com.example.presentation.shared.base

sealed class ErrorState {
    object NoInternet : ErrorState()
    data class RequestFailed(val message: String? = "Request failed") : ErrorState()
    object RequestTimeout : ErrorState()
}