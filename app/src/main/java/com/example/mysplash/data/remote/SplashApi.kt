package com.example.mysplash.data.remote


import com.example.mysplash.data.util.Constants.ACCESS_KEY
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SplashApi {
    @GET("photos/?client_id=$ACCESS_KEY")
    fun getSplashPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order_by") orderBy: String
    ) : Call<List<SplashPhotosItem>>
}