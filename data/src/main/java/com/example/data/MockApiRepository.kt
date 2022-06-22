package com.example.data

import android.content.Context
import com.example.data.utils.getJsonDataFromAsset
import com.example.domain.base.ResultWrapper
import com.example.domain.models.HistoricalDataItem
import com.example.domain.repositories.ApiRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MockApiRepository(
    private val applicationContext: Context
) : ApiRepository {

    private val gson = Gson()

    override suspend fun getHistoricalData() : ResultWrapper<List<HistoricalDataItem>> {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "historic_data.json")
        val historicDataResponseType = object : TypeToken<HistoricalDataResponse>() {}.type

        val historicalDataResponse: HistoricalDataResponse = gson.fromJson(jsonFileString, historicDataResponseType)

        return ResultWrapper.Success(historicalDataResponse.map { it.toDomainModel() })
    }

    override suspend fun getLiveData() {
        TODO("Not yet implemented")
    }
}