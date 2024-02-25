package com.example.workmanager_demo.dataSource

import com.example.workmanager_demo.model.TweetListItem
import com.example.workmanager_demo.service.TweetsApi
import com.example.workmanager_demo.dataSource.TweetsNetworkDataSource
import retrofit2.Response
import javax.inject.Inject

class TweetsNetworkDataSourceImpl @Inject constructor(private val tweetsApi: TweetsApi) :
    TweetsNetworkDataSource {
    override suspend fun fetchTopicsFromRemote(): Response<List<String>> {
        return tweetsApi.getTopics()
    }

    override suspend fun fetchTweetsForTopicFromRemote(topic: String): Response<List<TweetListItem>> {
        return tweetsApi.getTweets(topic = ("tweets[?(@.topic==\"$topic\")]"))
    }
}