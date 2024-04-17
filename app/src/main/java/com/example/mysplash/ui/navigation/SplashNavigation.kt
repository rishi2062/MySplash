package com.example.mysplash.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mysplash.data.ResponseState.UiState
import com.example.mysplash.data.remote.SplashPhotosItem
import com.example.mysplash.data.remote.SplashPhotosResponse
import com.example.mysplash.ui.HomeScreen
import com.example.mysplash.ui.SplashUiEvent
import com.example.mysplash.ui.SplashViewModel

fun NavGraphBuilder.navigateToHomeScreen() {
    composable("home_screen") {
        val splashViewModel : SplashViewModel = hiltViewModel()
        val splashData by splashViewModel.splashUiState.collectAsStateWithLifecycle()

        LaunchedEffect(key1 = Unit) {
            if(splashData !is UiState.Success){
                splashViewModel.getSplashPhotos(1, 30, "latest")
            }
        }

        when(splashData){
            is UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize()
                )
                Log.d("Rishi","Loading")
            }
            is UiState.Error -> {
                Log.d("Rishi","Error")
            }
            is UiState.Success -> {
                Log.d("Rishi","Success")
                HomeScreen(
                    splashPhotos = (splashData as UiState.Success<List<SplashPhotosItem>>).data,
                    splashUiEvent = {event ->
                        when(event){
                            is SplashUiEvent.ChangeFilter -> {
                                splashViewModel.getSplashPhotos(1, 30, event.filter)
                            }

                            SplashUiEvent.ChangePage -> {

                            }
                            is SplashUiEvent.ImagePerPage -> {

                            }
                        }
                    }
                )
            }

            UiState.Idle -> {
                Log.d("Rishi","Idle")
            }
            UiState.NoInternet -> {
                Log.d("Rishi","No Internet")
            }
        }
    }
}