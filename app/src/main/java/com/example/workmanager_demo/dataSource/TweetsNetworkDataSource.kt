package com.example.workmanager_demo.dataSource

import com.example.workmanager_demo.model.TweetListItem
import retrofit2.Response

interface TweetsNetworkDataSource {

    suspend fun fetchTopicsFromRemote(): Response<List<String>>

    suspend fun fetchTweetsForTopicFromRemote(topic: String): Response<List<TweetListItem>>
}