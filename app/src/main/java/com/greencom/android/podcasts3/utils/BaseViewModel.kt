package com.greencom.android.podcasts3.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<E, SE> : ViewModel() {

    private val _sideEffects = Channel<SE>(Channel.UNLIMITED)
    val sideEffects = _sideEffects.receiveAsFlow()

    abstract fun onEvent(event: E)

    protected fun emitSideEffect(sideEffect: SE) {
        _sideEffects.trySend(sideEffect)
    }

}
