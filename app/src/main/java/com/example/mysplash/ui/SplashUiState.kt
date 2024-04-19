//package com.example.mysplash.ui
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//
//
//@Composable
//fun rememberSplashUiState(
//    isLoading: Boolean = false,
//    splashUiEvent: (SplashUiEvent) -> Unit
//): SplashUiState {
//    var currPage by remember{
//        mutableStateOf(1)
//    }
//    return remember{
//        SplashUiState(isLoading,
//            splashUiEvent = splashUiEvent,
//            currPage  = currPage)
//    }
//}
//
//class SplashUiState(
//    val isLoading: Boolean = false,
//    val splashUiEvent: (SplashUiEvent) -> Unit,
//    var currPage : Int,
//){
//    var currentPage = mutableStateOf(currPage)
//        private set
//
//    fun loadNextPage(){
//        currentPage.value++
//        splashUiEvent(SplashUiEvent.ChangePage(currentPage.value))
//    }
//}
