package com.greencom.android.podcasts3.ui.common.screenbehaviors.navigationbar

import com.greencom.android.podcasts3.utils.screenbehavior.ScreenBehavior

sealed class NavigationBarBehavior : ScreenBehavior {
    abstract val isAnimated: Boolean
    data class Visible(override val isAnimated: Boolean) : NavigationBarBehavior()
    data class Hidden(override val isAnimated: Boolean) : NavigationBarBehavior()
}
