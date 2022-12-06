package com.greencom.android.podcasts3.utils.screenbehavior

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

@Stable
open class DefaultScreenBehaviorController<T : ScreenBehavior>(
    private var defaultBehavior: T,
) : ScreenBehaviorController<T> {

    private val behaviorStack = ArrayDeque<T>()

    private val _currentBehavior = mutableStateOf(defaultBehavior)
    override val currentBehavior: State<T> = _currentBehavior

    @Synchronized
    override fun setDefaultBehavior(behavior: T) {
        defaultBehavior = behavior
        updateCurrent()
    }

    @Synchronized
    override fun pushBehavior(behavior: T) {
        behaviorStack.addFirst(behavior)
        updateCurrent()
    }

    @Synchronized
    override fun popBehavior(behavior: T) {
        val lastIndex = behaviorStack.indexOfLast { it == behavior }
        if (lastIndex >= 0) behaviorStack.removeAt(lastIndex)
        updateCurrent()
    }

    private fun updateCurrent() {
        _currentBehavior.value = behaviorStack.firstOrNull() ?: defaultBehavior
    }

}
