package com.example.matiaslevwallboxchallenge.di

import com.example.data.MockApiRepository
import com.example.domain.actions.GetHistoricalData
import com.example.domain.repositories.ApiRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val actionsModule = module {
    single { GetHistoricalData(get()) }
}

val respositoriesModule = module {
    single<ApiRepository> { MockApiRepository(androidContext()) }
}