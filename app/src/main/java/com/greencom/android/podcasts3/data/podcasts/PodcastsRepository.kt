package com.greencom.android.podcasts3.data.podcasts

import javax.inject.Inject

class PodcastsRepository @Inject constructor(
    private val remoteDataSource: PodcastsRemoteDataSource,
)
