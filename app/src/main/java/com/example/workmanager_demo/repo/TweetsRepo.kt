package com.example.workmanager_demo.repo

import com.example.workmanager_demo.model.TweetListItem

interface TweetsRepo {
    suspend fun getTopics(): Result<List<String>?>

    suspend fun getTweetsByTopic(topic: String): Result<List<TweetListItem>?>
}