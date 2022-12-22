package com.greencom.android.podcasts3.data.podcasts.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetBestPodcastsResponse(
    @SerialName("id") val genreId: Int? = null,
    @SerialName("name") val genreName: String? = null,
    @SerialName("total") val total: Int? = null,
    @SerialName("page_number") val pageNumber: Int? = null,
    @SerialName("podcasts") val podcasts: List<PodcastDto>? = null,
    @SerialName("has_next") val hasNextPage: Boolean? = null,
    @SerialName("has_previous") val hasPreviousPage: Boolean? = null,
    @SerialName("next_page_number") val nextPageNumber: Int? = null,
    @SerialName("previous_page_number") val previousPageNumber: Int? = null,
) {

    @Serializable
    data class PodcastDto(
        @SerialName("id") val id: String? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("description") val description: String? = null,
        @SerialName("image") val imageUrl: String? = null,
        @SerialName("publisher") val publisher: String? = null,
        @SerialName("explicit_content") val explicitContent: Boolean? = null,
    )
}
