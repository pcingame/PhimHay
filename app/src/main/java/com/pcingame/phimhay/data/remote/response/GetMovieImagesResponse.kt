package com.pcingame.phimhay.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetMovieImagesResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("backdrops")
    val backdrops: List<BackdropResponse?>? = null,
    @SerializedName("posters")
    val posters: List<PosterResponse?>? = null
)

data class BackdropResponse(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double? = null,
    @SerializedName("file_path")
    val filePath: String? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Int? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("width")
    val width: Int? = null
)

data class PosterResponse(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double? = null,
    @SerializedName("file_path")
    val filePath: String? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Int? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("width")
    val width: Int? = null
)
