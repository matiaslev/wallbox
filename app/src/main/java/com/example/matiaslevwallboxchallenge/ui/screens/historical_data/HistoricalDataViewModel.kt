package com.example.matiaslevwallboxchallenge.ui.screens.historical_data

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.domain.actions.GetHistoricalData
import com.example.domain.models.HistoricalDataItem
import com.example.matiaslevwallboxchallenge.ui.base.BaseAction
import com.example.matiaslevwallboxchallenge.ui.base.BaseViewModel
import com.example.matiaslevwallboxchallenge.ui.base.BaseViewState
import kotlinx.coroutines.launch

class HistoricalDataViewModel(
    private val getHistoricalData: GetHistoricalData
) : BaseViewModel<HistoricalDataViewModel.ViewState, HistoricalDataViewModel.Action>(ViewState()) {

    override val viewModelName: String = HistoricalDataViewModel::class.java.simpleName

    override fun onLoadData() {
        onGetHistoricalData()
    }

    fun onGetHistoricalData() {
        sendAction(Action.Loading)
        Log.d(viewModelName, getHistoricalData.name)
        viewModelScope.launch {
            getHistoricalData().also { result ->
                val action = when (result) {
                    is GetHistoricalData.Result.Success -> Action.HistoricalDataSuccess(result.historicalData)
                    GetHistoricalData.Result.NetworkError -> TODO()
                    is GetHistoricalData.Result.ErrorResponse -> TODO()
                }
                sendAction(action)
            }
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when(viewAction) {
        Action.Loading -> state.copy(
            isLoading = true,
            historicalData = null
        )
        is Action.HistoricalDataSuccess -> state.copy(
            isLoading = true,
            historicalData = viewAction.historicalData
        )
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val historicalData: List<HistoricalDataItem>? = null
    ) : BaseViewState

    sealed class Action : BaseAction {
        object Loading : Action()
        data class HistoricalDataSuccess(val historicalData: List<HistoricalDataItem>) : Action()
    }
}