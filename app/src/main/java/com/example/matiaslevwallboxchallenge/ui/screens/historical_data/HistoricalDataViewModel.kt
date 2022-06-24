package com.example.matiaslevwallboxchallenge.ui.screens.historical_data

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.domain.actions.GetHistoricalData
import com.example.domain.models.HistoricalDataItem
import com.example.matiaslevwallboxchallenge.ui.base.BaseAction
import com.example.matiaslevwallboxchallenge.ui.base.BaseViewModel
import com.example.matiaslevwallboxchallenge.ui.base.BaseViewState
import com.example.matiaslevwallboxchallenge.ui.widgets.base.ViewStateType
import kotlinx.coroutines.launch

class HistoricalDataViewModel(
    private val getHistoricalData: GetHistoricalData
) : BaseViewModel<HistoricalDataViewModel.ViewState, HistoricalDataViewModel.Action>(ViewState()) {

    override val viewModelName: String = HistoricalDataViewModel::class.java.simpleName

    override fun onLoadData() {
        onGetHistoricalData()
    }

    private fun onGetHistoricalData() {
        lastIntention = { onGetHistoricalData() }
        sendAction(Action.Loading)
        Log.d(viewModelName, getHistoricalData.name)
        viewModelScope.launch {
            getHistoricalData().also { result ->
                val action = when (result) {
                    is GetHistoricalData.Result.Success -> Action.HistoricalDataSuccess(result.historicalData)
                    is GetHistoricalData.Result.ErrorResponse -> Action.Error(result.message)
                    GetHistoricalData.Result.NetworkError -> Action.NetworkError
                }
                sendAction(action)
            }
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when (viewAction) {
        Action.Loading -> state.copy(
            viewStateType = ViewStateType.Loading,
            historicalData = null
        )
        is Action.HistoricalDataSuccess -> state.copy(
            viewStateType = if (viewAction.historicalData.isEmpty()) ViewStateType.Empty else ViewStateType.Success,
            historicalData = viewAction.historicalData
        )
        is Action.Error -> state.copy(
            viewStateType = ViewStateType.Error(viewAction.message),
            historicalData = null
        )
        Action.NetworkError -> state.copy(
            viewStateType = ViewStateType.NetworkError,
            historicalData = null
        )
    }

    data class ViewState(
        val viewStateType: ViewStateType = ViewStateType.Loading,
        val historicalData: List<HistoricalDataItem>? = null
    ) : BaseViewState

    sealed class Action : BaseAction {
        object Loading : Action()
        data class HistoricalDataSuccess(val historicalData: List<HistoricalDataItem>) : Action()
        data class Error(val message: String) : Action()
        object NetworkError : Action()
    }
}