package com.greencom.android.podcasts3.data.podcasts.remote

import com.greencom.android.podcasts3.data.podcasts.remote.dto.GetBestPodcastsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PodcastsApi {

    @GET("best_podcasts")
    suspend fun getBestPodcasts(
        @Query("genre_id") genreId: Int?,
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("sort") sort: String,
        @Query("safe_mode") isSafeModeEnabled: Int,
    ): GetBestPodcastsResponseDto

    enum class BestPodcastsSort(val stringValue: String) {
        ListenScore("listen_score"),
    }

}
