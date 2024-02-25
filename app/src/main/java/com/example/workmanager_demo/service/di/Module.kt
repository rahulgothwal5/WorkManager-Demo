package com.example.workmanager_demo.service.di

import com.example.workmanager_demo.service.TweetsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideTweetAPI(retrofit: Retrofit): TweetsApi {
        return retrofit.create(TweetsApi::class.java)
    }
}