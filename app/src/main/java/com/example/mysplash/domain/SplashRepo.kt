package com.example.mysplash.domain

import com.example.mysplash.data.ResponseState.ResponseState
import com.example.mysplash.data.remote.SplashPhotosItem
import com.example.mysplash.data.remote.SplashPhotosResponse

interface SplashRepo {
    suspend fun getSplashPhotos(
        page: Int,
        perPage: Int,
        orderBy: String
    ) : ResponseState<List<SplashPhotosItem>>
}