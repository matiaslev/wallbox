package com.example.data

import android.content.Context
import com.example.data.entities.HistoricalDataResponse
import com.example.data.entities.LiveDataResponse
import com.example.data.utils.getJsonDataFromAsset
import com.example.domain.base.ResultWrapper
import com.example.domain.models.HistoricalDataItem
import com.example.domain.models.LiveData
import com.example.domain.repositories.ApiRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay

class MockApiRepository(
    private val applicationContext: Context
) : ApiRepository {

    private val gson = Gson()

    private val localSourceOfTruth: LocalInMemorySourceOfTruth = LocalInMemorySourceOfTruth()

    override suspend fun getHistoricalData() : ResultWrapper<List<HistoricalDataItem>> {
        val historicalData = if (localSourceOfTruth.historicalData == null) {
            val jsonFileString = getJsonDataFromAsset(applicationContext, "historic_data.json")
            val type = object : TypeToken<HistoricalDataResponse>() {}.type

            val response: HistoricalDataResponse = gson.fromJson(jsonFileString, type)

            delay(3500)

             response.map { it.toDomainModel() }.apply {
                 localSourceOfTruth.historicalData = this
             }
        } else localSourceOfTruth.historicalData

        /*return historicalData?.let {
            ResultWrapper.Success(it)
        } ?: ResultWrapper.NetworkError*/ // NetworkError will not happen

        // return ResultWrapper.Error("Something went wrong")
        return ResultWrapper.NetworkError
    }

    override suspend fun getLiveData() : ResultWrapper<LiveData> {
        val liveData = if (localSourceOfTruth.liveData == null) {
            val jsonFileString = getJsonDataFromAsset(applicationContext, "live_data.json")
            val type = object : TypeToken<LiveDataResponse>() {}.type

            val response: LiveDataResponse = gson.fromJson(jsonFileString, type)

            delay(3500)

            response.toDomainModel().apply {
                localSourceOfTruth.liveData = this
            }
        } else localSourceOfTruth.liveData

        return liveData?.let {
            ResultWrapper.Success(it)
        } ?: ResultWrapper.NetworkError // NetworkError will not happen

        // return ResultWrapper.Error("Something went wrong")
    }

    data class LocalInMemorySourceOfTruth(
        var historicalData: List<HistoricalDataItem>? = null,
        var liveData: LiveData? = null
    )
}