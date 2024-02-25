package com.example.workmanager_demo.useCase

import com.example.workmanager_demo.Result
import com.example.workmanager_demo.repo.TweetsRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTweetsForTopicUseCase @Inject constructor(
    private val repository: TweetsRepo
) {

    operator fun invoke(topic: String) = flow {
        try {
            val result = repository.getTweetsByTopic(topic)
            if (result.isSuccess) {
                emit(Result.Success(result.getOrNull()))
            } else {
                emit(Result.Error(Exception("Failed to fetch tweets for topic '$topic': ${result.exceptionOrNull()?.message}")))
            }
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}