package com.example.matiaslevwallboxchallenge.ui.screens.live_data

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.domain.actions.GetLiveData
import com.example.domain.models.LiveData
import com.example.matiaslevwallboxchallenge.ui.base.BaseAction
import com.example.matiaslevwallboxchallenge.ui.base.BaseViewModel
import com.example.matiaslevwallboxchallenge.ui.base.BaseViewState
import com.example.matiaslevwallboxchallenge.ui.widgets.base.ViewStateType
import kotlinx.coroutines.launch

class LiveDataViewModel(
    private val getLiveData: GetLiveData
) : BaseViewModel<LiveDataViewModel.ViewState, LiveDataViewModel.Action>(ViewState()) {

    override val viewModelName: String = LiveDataViewModel::class.java.simpleName

    override fun onLoadData() {
        onGetLiveData()
    }

    private fun onGetLiveData() {
        lastIntention = { onGetLiveData() }
        sendAction(Action.Loading)
        Log.d(viewModelName, getLiveData.name)
        viewModelScope.launch {
            getLiveData().also { result ->
                val action = when (result) {
                    is GetLiveData.Result.Success -> Action.LiveDataSuccess(result.liveData)
                    is GetLiveData.Result.ErrorResponse -> Action.Error(result.message)
                    GetLiveData.Result.NetworkError -> Action.NetworkError
                }
                sendAction(action)
            }
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when(viewAction) {
        Action.Loading -> state.copy(
            viewStateType = ViewStateType.Loading,
            liveData = null
        )
        is Action.LiveDataSuccess -> state.copy(
            viewStateType = ViewStateType.Success,
            liveData = viewAction.liveData
        )
        is Action.Error -> state.copy(
            viewStateType = ViewStateType.Error(viewAction.message),
            liveData = null
        )
        Action.NetworkError -> state.copy(
            viewStateType = ViewStateType.NetworkError,
            liveData = null
        )
    }

    data class ViewState(
        val viewStateType: ViewStateType = ViewStateType.Start,
        val liveData: LiveData? = null
    ) : BaseViewState

    sealed class Action : BaseAction {
        object Loading : Action()
        data class LiveDataSuccess(val liveData: LiveData) : Action()
        data class Error(val message: String) : Action()
        object NetworkError : Action()
    }
}