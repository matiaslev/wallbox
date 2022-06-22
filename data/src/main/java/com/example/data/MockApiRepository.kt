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

class MockApiRepository(
    private val applicationContext: Context
) : ApiRepository {

    private val gson = Gson()

    override suspend fun getHistoricalData() : ResultWrapper<List<HistoricalDataItem>> {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "historic_data.json")
        val type = object : TypeToken<HistoricalDataResponse>() {}.type

        val response: HistoricalDataResponse = gson.fromJson(jsonFileString, type)

        return ResultWrapper.Success(response.map { it.toDomainModel() })
    }

    override suspend fun getLiveData() : ResultWrapper<LiveData> {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "live_data.json")
        val type = object : TypeToken<LiveDataResponse>() {}.type

        val response: LiveDataResponse = gson.fromJson(jsonFileString, type)

        return ResultWrapper.Success(response.toDomainModel())
    }
}