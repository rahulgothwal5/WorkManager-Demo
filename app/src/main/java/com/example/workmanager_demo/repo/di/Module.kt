package com.example.workmanager_demo.repo.di

import com.example.workmanager_demo.dataSource.TweetsNetworkDataSource
import com.example.workmanager_demo.repo.TweetsRepo
import com.example.workmanager_demo.repo.TweetsRepoImpl
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
    fun provideTweetsRepo(tweetsNetworkDataSource: TweetsNetworkDataSource): TweetsRepo {
        return TweetsRepoImpl(tweetsNetworkDataSource)
    }
}