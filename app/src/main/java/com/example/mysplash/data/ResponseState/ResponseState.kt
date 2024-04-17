package com.example.mysplash.data.ResponseState

import retrofit2.Response


sealed interface ResponseState<out T> {
    data class Success<R>(val data: R) : ResponseState<R>
    data class Error<R>(val errorMessage: String) : ResponseState<R>
    data object NoInternet : ResponseState<Nothing>
    data object Loading : ResponseState<Nothing>
}

fun <T> ResponseState<T>.toUiState() : UiState<T>{
    return when(this){
        is ResponseState.Success -> UiState.Success(this.data)
        is ResponseState.Error -> UiState.Error(this.errorMessage)
        is ResponseState.NoInternet -> UiState.NoInternet
        is ResponseState.Loading -> UiState.Loading
    }
}

suspend fun <T> getApiResponseState(
    onSuccess: suspend (Response<T>) -> Unit = {},
    onFailure: suspend (Exception) -> Unit = {},
    request: suspend () -> Response<T>
) : ResponseState<T>{
    return try {
        val response = request()
        onSuccess(response)

        if(response.isSuccessful){
            if (response.body() != null){
                ResponseState.Success(response.body()!!)
            }else{
                ResponseState.Error("Null data")
            }
        }
        else{
            ResponseState.Error("An error occurred")
        }
//        when(response.code()){
//            in 200..299 -> {
//                if (response.body() != null){
//                    ResponseState.Success(response.body()!!)
//                }else{
//                    ResponseState.Error("Null data")
//                }
//            }
//            else -> {
//                ResponseState.Error("An error occurred")
//            }
//        }
    }
    catch (e: Exception){
        onFailure(e)
        ResponseState.Error(e.message ?: "An error occurred")
    }
}