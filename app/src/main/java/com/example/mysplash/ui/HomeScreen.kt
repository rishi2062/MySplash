package com.example.mysplash.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mysplash.data.remote.SplashPhotosItem
import com.example.mysplash.ui.components.OrderByCard
import com.example.mysplash.ui.components.SplashImageCard

@Composable
fun HomeScreen(
    splashPhotos : List<SplashPhotosItem>,
    splashUiEvent: (SplashUiEvent) -> Unit
) {
    Scaffold(
        topBar = {
            OrderByCard(splashUiEvent = splashUiEvent)
        },
        content = {
            HomeScreenContent(splashPhotos = splashPhotos, splashUiEvent = splashUiEvent,
                modifier = Modifier.padding(it))
        }
    )
}

@Composable
fun HomeScreenContent(
    modifier : Modifier = Modifier,
    splashPhotos : List<SplashPhotosItem>,
    splashUiEvent: (SplashUiEvent) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(150.dp)) {
            val itemSize = if(splashPhotos.size % 2 == 0){
                splashPhotos.size / 2
            }
            else{
                (splashPhotos.size / 2) + 1
            }
            items(splashPhotos.size){
                if(it >= itemSize - 1){
                    splashUiEvent(SplashUiEvent.ChangePage)
                }
                SplashImageCard(image = splashPhotos[it].urls.raw, description = splashPhotos[it].alt_description ?: "No Description Found")
//                Text(text = splashPhotos[it].alt_description ?: "No Description Found")
            }
        }
    }
}




