package com.example.matiaslevwallboxchallenge.di

import com.example.data.MockApiRepository
import com.example.domain.actions.GetHistoricalData
import com.example.domain.actions.GetLiveData
import com.example.domain.logger.Logger
import com.example.domain.repositories.ApiRepository
import com.example.matiaslevwallboxchallenge.logger.LoggerImpl
import com.example.matiaslevwallboxchallenge.ui.screens.historical_data.HistoricalDataViewModel
import com.example.matiaslevwallboxchallenge.ui.screens.live_data.LiveDataViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelsModule = module {
    viewModel { LiveDataViewModel(get(), get()) }
    viewModel { HistoricalDataViewModel(get(), get()) }
}

val actionsModule = module {
    single { GetHistoricalData(get()) }
    single { GetLiveData(get()) }
}

val repositoriesModule = module {
    single<ApiRepository> { MockApiRepository(androidContext()) }
}

val utilsModule = module {
    single<Logger> { LoggerImpl() }
}