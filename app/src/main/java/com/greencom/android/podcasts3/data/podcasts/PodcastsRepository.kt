package com.greencom.android.podcasts3.data.podcasts

import com.greencom.android.podcasts3.data.podcasts.local.PodcastsLocalDataSource
import com.greencom.android.podcasts3.data.podcasts.remote.PodcastsRemoteDataSource
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class PodcastsRepository @Inject constructor(
    private val localDataSource: PodcastsLocalDataSource,
    private val remoteDataSource: PodcastsRemoteDataSource,
) {

    private val subscriptionIds: StateFlow<Set<String>> = localDataSource.subscriptionIds

}
