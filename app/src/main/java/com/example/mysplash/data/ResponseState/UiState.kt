package com.example.mysplash.data.ResponseState


sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data object Idle : UiState<Nothing>
    data object NoInternet : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}