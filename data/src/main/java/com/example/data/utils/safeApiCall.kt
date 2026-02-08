package com.example.data.utils

import android.util.Log
import com.example.domain.exception.NoInternetException
import com.example.domain.exception.NotFoundException
import com.example.domain.exception.UnauthorizedException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.io.IOException

suspend inline fun <reified T> safeApiCall(
    execute: suspend () -> HttpResponse
): T {
    val result = try {
        execute()
    } catch (exception: IOException) {
        Log.e(SAFE_API_CALL_TAG, "IOException ${exception.message}" )
        throw NoInternetException()
    } catch (exception: UnresolvedAddressException) {
        Log.e(SAFE_API_CALL_TAG, "UnresolvedAddressException ${exception.message}" )
        throw NoInternetException()
    } catch (exception: Exception) {
        Log.e(SAFE_API_CALL_TAG, "Unknown exception ${exception.message}" )
        throw exception
    }

    return handleResponseStatusCode(result)
}

suspend inline fun <reified T> handleResponseStatusCode(result: HttpResponse): T {
    return when (result.status.value) {
        in 200..299 -> {
            result.body<T>()
        }

        in 400..499 -> {
            when (result.status) {
                HttpStatusCode.Unauthorized -> {
                    Log.e(HANDLE_ERROR_STATUS_TAG, "handleResponseStatusCode: ${result.status.value}")
                    throw UnauthorizedException()
                }

                HttpStatusCode.NotFound -> {
                    Log.e(HANDLE_ERROR_STATUS_TAG, "handleResponseStatusCode: ${result.status.value}")
                    throw NotFoundException()
                }

                else -> {
                    Log.e(HANDLE_ERROR_STATUS_TAG, "handleResponseStatusCode: ${result.status.value}")
                    throw Exception()
                }
            }
        }

        else -> {
            Log.e(HANDLE_ERROR_STATUS_TAG, "handleResponseStatusCode: ${result.status.value}")
            throw Exception()
        }
    }
}

const val SAFE_API_CALL_TAG = "safeApiCall"
const val HANDLE_ERROR_STATUS_TAG = "handleErrorStatus"