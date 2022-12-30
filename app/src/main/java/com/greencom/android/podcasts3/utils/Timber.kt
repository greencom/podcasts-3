package com.greencom.android.podcasts3.utils

import timber.log.Timber

val Timber.Forest.isInitialized: Boolean
    get() = treeCount > 0
