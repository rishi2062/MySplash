package com.example.mysplash.data.di

import com.example.mysplash.data.remote.SplashApi
import com.example.mysplash.data.util.Constants.BASE_URL
import com.example.mysplash.domain.SplashRepo
import com.example.mysplash.data.remote.SplashRepoImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {
    @Singleton
    @Provides
    fun provideSplashApi() : SplashApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SplashApi::class.java)
    }

    @Singleton
    @Provides
    fun providesSplashRepo(splashApi: SplashApi) : SplashRepo {
        return SplashRepoImplementation(splashApi)
    }

}