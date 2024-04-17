package com.example.mysplash.domain

import com.example.mysplash.data.ResponseState.ResponseState
import com.example.mysplash.data.ResponseState.getApiResponseState
import com.example.mysplash.data.remote.SplashApi
import com.example.mysplash.data.remote.SplashPhotosItem
import com.example.mysplash.data.remote.SplashPhotosResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashRepoImplementation
@Inject constructor(private val splashApi: SplashApi) : SplashRepo {
    //    override suspend fun getSplashPhotos(
//        page: Int,
//        perPage: Int,
//        orderBy: String
//    ): ResponseState<List<SplashPhotosItem>> {
//        return getApiResponseState {
//            splashApi.getSplashPhotos(
//                page = page,
//                perPage = perPage,
//                orderBy = orderBy
//            )
//        }
//    }
    override suspend fun getSplashPhotos(
        page: Int,
        perPage: Int,
        orderBy: String
    ): ResponseState<List<SplashPhotosItem>> =
        withContext(Dispatchers.IO) {
            getApiResponseState {
                splashApi.getSplashPhotos(
                    page = page,
                    perPage = perPage,
                    orderBy = orderBy
                ).execute()
            }
        }
//        val res = splashApi.getSplashPhotos(page, perPage, orderBy).execute()
//        if (res.isSuccessful) {
//            res.body()?.let {
//                return@withContext ResponseState.Success(it)
//            }
//        }
//            return@withContext ResponseState.Error(res.message())
//    }
}