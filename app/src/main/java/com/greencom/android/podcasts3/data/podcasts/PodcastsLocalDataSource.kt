package com.greencom.android.podcasts3.data.podcasts

import com.greencom.android.podcasts3.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PodcastsLocalDataSource @Inject constructor(
    @ApplicationScope private val applicationScope: CoroutineScope,
) {

    private val _subscriptionIds = MutableStateFlow(HashSet<String>())
    val subscriptionIds: StateFlow<Set<String>> = _subscriptionIds.asStateFlow()

    init {
        fetchSubscriptionIds()
    }

    private fun fetchSubscriptionIds() {
        // TODO
    }

}
