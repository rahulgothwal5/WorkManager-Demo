package com.example.workmanager_demo.dataSource.di

import com.example.workmanager_demo.dataSource.TweetsNetworkDataSource
import com.example.workmanager_demo.dataSource.TweetsNetworkDataSourceImpl
import com.example.workmanager_demo.service.TweetsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideTweetsNetworkDataSource(tweetsApi: TweetsApi): TweetsNetworkDataSource {
        return TweetsNetworkDataSourceImpl(tweetsApi)
    }
}