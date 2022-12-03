package com.greencom.android.podcasts3.ui.player

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts3.ui.common.compositionlocalproviders.LocalNavigationBarSizeTracker
import kotlin.math.roundToInt

// TODO: Disable swipe to hidden state

private const val ThresholdFraction = 0.2f
private val PeekHeight = 56.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayerBottomSheet(
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier = modifier) {
        val maxHeightPx = constraints.maxHeight
        val navigationBarSizeTracker = LocalNavigationBarSizeTracker.current
        val navigationBarHeightPx = navigationBarSizeTracker.sizePx.value.height
        val density = LocalDensity.current
        val peekHeightPx = remember(density) {
            with(density) { PeekHeight.toPx() }
        }
        val systemNavigationBarsHeightPx = WindowInsets.navigationBars.getBottom(density)

        val anchors = remember(
            maxHeightPx,
            navigationBarHeightPx,
            peekHeightPx,
            systemNavigationBarsHeightPx,
        ) {
            val collapsedAnchor = calculateCollapsedAnchor(
                maxHeightPx = maxHeightPx.toFloat(),
                navigationBarHeightPx = navigationBarHeightPx.toFloat(),
                peekHeightPx = peekHeightPx,
                systemNavigationBarsHeightPx = systemNavigationBarsHeightPx.toFloat(),
            )
            mapOf(
                0f to PlayerBottomSheetState.Expanded,
                collapsedAnchor to PlayerBottomSheetState.Collapsed,
                maxHeightPx.toFloat() to PlayerBottomSheetState.Hidden,
            )
        }
        val swipeableState = rememberSwipeableState(
            initialValue = PlayerBottomSheetState.Collapsed,
            confirmStateChange = { it != PlayerBottomSheetState.Hidden },
        )
        val fractionalThreshold = remember {
            FractionalThreshold(ThresholdFraction)
        }
        val resistanceConfig = remember {
            ResistanceConfig(0f, 0f, 0f)
        }

        Box(
            modifier = Modifier
                .offset {
                    IntOffset(0, swipeableState.offset.value.roundToInt())
                }
                .fillMaxSize()
                .background(Color.DarkGray) // TODO: Remove color when content is ready
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    orientation = Orientation.Vertical,
                    thresholds = { _, _ -> fractionalThreshold },
                    resistance = resistanceConfig,
                ),
        )
    }
}

@Stable
private fun calculateCollapsedAnchor(
    maxHeightPx: Float,
    navigationBarHeightPx: Float,
    peekHeightPx: Float,
    systemNavigationBarsHeightPx: Float,
): Float {
    return if (navigationBarHeightPx >= systemNavigationBarsHeightPx) {
        maxHeightPx - navigationBarHeightPx - peekHeightPx
    } else {
        maxHeightPx - systemNavigationBarsHeightPx - peekHeightPx
    }
}
