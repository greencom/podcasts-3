package com.greencom.android.podcasts3.data.podcasts.remote

import com.greencom.android.podcasts3.utils.toInt
import javax.inject.Inject

class PodcastsRemoteDataSource @Inject constructor(
    private val api: PodcastsApi,
) {

    suspend fun getBestPodcasts(
        genreId: Int?,
        page: Int,
        language: String,
        sort: PodcastsApi.BestPodcastsSort = PodcastsApi.BestPodcastsSort.ListenScore,
        isSafeModeEnabled: Boolean = false,
    ) {
        // TODO: Handle HTTP errors
        val dto = api.getBestPodcasts(
            genreId = genreId,
            page = page,
            language = language,
            sort = sort.stringValue,
            isSafeModeEnabled = isSafeModeEnabled.toInt(),
        )
    }

}
