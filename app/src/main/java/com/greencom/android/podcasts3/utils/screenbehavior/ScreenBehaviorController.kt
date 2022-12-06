package com.greencom.android.podcasts3.utils.screenbehavior

import androidx.compose.runtime.State

interface ScreenBehaviorController<T : ScreenBehavior> {
    val currentBehavior: State<T>
    fun setDefaultBehavior(behavior: T)
    fun pushBehavior(behavior: T)
    fun popBehavior(behavior: T)
}
