package com.greencom.android.podcasts3.ui.common.baseviewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<E: Event, SE: SideEffect> : ViewModel() {

    private val _sideEffects = Channel<SE>(Channel.UNLIMITED)
    val sideEffects = _sideEffects.receiveAsFlow()

    abstract fun onEvent(event: E)

    private fun emitSideEffect(sideEffect: SE) {
        _sideEffects.trySend(sideEffect)
    }

}
