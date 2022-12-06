package com.greencom.android.podcasts3.utils.screenbehavior

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class NoOpScreenBehaviorController<T : ScreenBehavior>(
    defaultBehavior: T,
) : ScreenBehaviorController<T> {

    override val currentBehavior: State<T> = mutableStateOf(defaultBehavior)

    override fun setDefaultBehavior(behavior: T) = Unit

    override fun pushBehavior(behavior: T) = Unit

    override fun popBehavior(behavior: T) = Unit

}
