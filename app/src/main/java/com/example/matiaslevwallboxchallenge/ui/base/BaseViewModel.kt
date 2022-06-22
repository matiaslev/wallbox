package com.example.matiaslevwallboxchallenge.ui.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ViewState : BaseViewState, ViewAction : BaseAction>(initialState: ViewState) : ViewModel() {

    protected abstract val viewModelName: String

    private val stateMutable = mutableStateOf(initialState)
    val state by stateMutable

    fun sendAction(viewAction: ViewAction) {
        stateMutable.value = onReduceState(viewAction)
    }

    protected abstract fun onReduceState(viewAction: ViewAction): ViewState

    fun loadData() {
        onLoadData()
    }

    protected open fun onLoadData() {}
}

interface BaseViewState

interface BaseAction