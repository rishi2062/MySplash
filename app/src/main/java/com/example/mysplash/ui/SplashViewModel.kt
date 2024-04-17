package com.example.mysplash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysplash.data.ResponseState.UiState
import com.example.mysplash.data.ResponseState.toUiState
import com.example.mysplash.data.remote.SplashPhotosItem
import com.example.mysplash.data.remote.SplashPhotosResponse
import com.example.mysplash.domain.SplashRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repo : SplashRepo
): ViewModel() {
//    private val _uiState = mutableStateOf(SplashUiState())
//    val uiState : State<SplashUiState> = _uiState

    private val _splashUiState = MutableStateFlow<UiState<List<SplashPhotosItem>>>(UiState.Idle)
    val splashUiState = _splashUiState.asStateFlow()

    private val _splashPhotos : MutableStateFlow<SplashPhotosItem> = MutableStateFlow(SplashPhotosItem())

//    init {
//        getSplashPhotos(1, 10, "latest")
//    }

    fun getSplashPhotos(page: Int, perPage: Int, orderBy: String){
        _splashUiState.value = UiState.Loading
        viewModelScope.launch {
           val response = repo.getSplashPhotos(page, perPage, orderBy)
            _splashUiState.update {
                response.toUiState()
            }
        }
    }
}