package com.example.mysplash.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
//import com.example.mysplash.ui.SplashUiState
import com.example.mysplash.ui.SplashViewModel

fun NavGraphBuilder.navigateToHomeScreen() {
    composable("home_screen") {
        val splashViewModel : SplashViewModel = hiltViewModel()
        val splashData by splashViewModel.splashUiState.collectAsStateWithLifecycle()
        var currPage by remember {
            mutableStateOf(1)
        }
        var splashPhotos by remember {
            mutableStateOf(listOf<SplashPhotosItem>())
        }
        LaunchedEffect(key1 = Unit) {
            if(splashData !is UiState.Success){
                splashViewModel.getSplashPhotos(currPage, 30, "latest")
            }
        }


        when(splashData){
            is UiState.Loading -> {
                Column( modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    CircularProgressIndicator()
                }
                Log.d("Rishi","Loading")
            }
            is UiState.Error -> {
                Log.d("Rishi","Error")
            }
            is UiState.Success -> {
                Log.d("Rishi","Success")
                splashPhotos = splashPhotos + (splashData as UiState.Success<List<SplashPhotosItem>>).data
                HomeScreen(
                    splashPhotos = splashPhotos,
                    splashUiEvent = {event ->
                        when(event){
                            is SplashUiEvent.ChangeFilter -> {
                                splashViewModel.getSplashPhotos(currPage, 30, event.filter)
                            }

                            SplashUiEvent.ChangePage -> {
                                currPage++
                                Log.d("Rishi","ChangePage $currPage")
                                splashViewModel.getSplashPhotos(currPage, 30, "latest")
                            }
                            is SplashUiEvent.ImagePerPage -> {

                            }
                            is SplashUiEvent.LoadMore ->  {

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