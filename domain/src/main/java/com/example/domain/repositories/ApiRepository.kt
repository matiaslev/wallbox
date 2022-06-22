package com.example.domain.repositories

import com.example.domain.base.ResultWrapper
import com.example.domain.models.HistoricalDataItem
import com.example.domain.models.LiveData

interface ApiRepository {
    suspend fun getHistoricalData(): ResultWrapper<List<HistoricalDataItem>>
    suspend fun getLiveData(): ResultWrapper<LiveData>
}