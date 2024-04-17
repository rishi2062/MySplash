package com.example.mysplash.ui

sealed class SplashUiEvent {
    data class ChangeFilter(val filter: String) : SplashUiEvent()
    data object ChangePage : SplashUiEvent()
    data class ImagePerPage(val perPage: Int) : SplashUiEvent()
}