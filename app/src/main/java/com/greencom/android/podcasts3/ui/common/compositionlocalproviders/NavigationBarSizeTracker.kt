package com.greencom.android.podcasts3.ui.common.compositionlocalproviders

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.IntSize

val LocalNavigationBarSizeTracker = staticCompositionLocalOf {
    NavigationBarSizeTracker()
}

class NavigationBarSizeTracker {

    private val _sizePx = mutableStateOf(IntSize(0, 0))
    val sizePx: State<IntSize> = _sizePx

    fun onSizeChanged(size: IntSize) {
        _sizePx.value = size
    }

}
