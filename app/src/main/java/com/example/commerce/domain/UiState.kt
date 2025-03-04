package com.example.commerce.domain

sealed class UiState<out T> {
    object LOADING: UiState<Nothing>()

    data class SUCCESS<T>(val response: T): UiState<T>()

    data class ERROR(val error: Exception): UiState<Nothing>()
}