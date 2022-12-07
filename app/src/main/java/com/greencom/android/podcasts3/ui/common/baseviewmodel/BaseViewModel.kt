package com.greencom.android.podcasts3.ui.common.baseviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.plus

abstract class BaseViewModel<E: Event, SE: SideEffect> : ViewModel() {

    private val defaultScope = viewModelScope + Dispatchers.Default

    private val _sideEffects = Channel<SE>(Channel.UNLIMITED)
    val sideEffects = _sideEffects.receiveAsFlow()

    abstract fun onEvent(event: E)

    private fun emitSideEffect(sideEffect: SE) {
        _sideEffects.trySend(sideEffect)
    }

}
