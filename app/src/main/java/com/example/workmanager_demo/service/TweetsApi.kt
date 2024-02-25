package com.example.workmanager_demo.service

import com.example.workmanager_demo.model.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsApi {

    @GET("/v3/b/65c7a7971f5677401f2d84cf?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") topic: String): Response<List<TweetListItem>>

    @GET("/v3/b/65c7a7971f5677401f2d84cf?meta=false")
    @Headers("X-JSON-Path: tweets..topic")
    suspend fun getTopics(): Response<List<String>>
}