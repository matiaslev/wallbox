package com.example.domain.actions

import com.example.domain.base.BaseAction
import com.example.domain.base.ResultWrapper
import com.example.domain.models.HistoricalDataItem
import com.example.domain.repositories.ApiRepository

class GetHistoricalData(
    private val apiRepository: ApiRepository
) : BaseAction() {

    override val name: String = GetHistoricalData::class.java.simpleName

    sealed class Result {
        data class Success(val historicalData: List<HistoricalDataItem>) : Result()
        data class ErrorResponse(val message: String) : Result()
        object NetworkError : Result()
    }

    suspend operator fun invoke(): Result {
        return when (val resultWrapper = apiRepository.getHistoricalData()) {
            is ResultWrapper.Success -> Result.Success(resultWrapper.value)
            is ResultWrapper.Error -> Result.ErrorResponse(resultWrapper.description)
            ResultWrapper.NetworkError -> Result.NetworkError
        }
    }
}