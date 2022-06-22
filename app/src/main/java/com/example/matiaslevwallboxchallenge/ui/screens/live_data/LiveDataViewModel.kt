package com.example.matiaslevwallboxchallenge.ui.screens.live_data

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.domain.actions.GetLiveData
import com.example.domain.models.LiveData
import com.example.matiaslevwallboxchallenge.ui.base.BaseAction
import com.example.matiaslevwallboxchallenge.ui.base.BaseViewModel
import com.example.matiaslevwallboxchallenge.ui.base.BaseViewState
import kotlinx.coroutines.launch

class LiveDataViewModel(
    private val getLiveData: GetLiveData
) : BaseViewModel<LiveDataViewModel.ViewState, LiveDataViewModel.Action>(LiveDataViewModel.ViewState()) {

    override val viewModelName: String = LiveDataViewModel::class.java.simpleName

    override fun onLoadData() {
        onGetLiveData()
    }

    fun onGetLiveData() {
        sendAction(Action.Loading)
        Log.d(viewModelName, getLiveData.name)
        viewModelScope.launch {
            getLiveData().also { result ->
                val action = when (result) {
                    is GetLiveData.Result.Success -> Action.LiveDataSuccess(result.liveData)
                    GetLiveData.Result.NetworkError -> TODO()
                    is GetLiveData.Result.ErrorResponse -> TODO()
                }
                sendAction(action)
            }
        }
    }

    override fun onReduceState(viewAction: Action): ViewState = when(viewAction) {
        Action.Loading -> state.copy(
            isLoading = true,
            liveData = null
        )
        is Action.LiveDataSuccess -> state.copy(
            isLoading = false,
            liveData = viewAction.liveData
        )
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val liveData: LiveData? = null
    ) : BaseViewState

    sealed class Action : BaseAction {
        object Loading : Action()
        data class LiveDataSuccess(val liveData: LiveData) : Action()
    }
}