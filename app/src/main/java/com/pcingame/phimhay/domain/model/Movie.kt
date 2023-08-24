package com.pcingame.phimhay.domain.model

import android.os.Parcelable
import com.pcingame.phimhay.BuildConfig
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val adult: Boolean? = false,
    val backdropPath: String? = null,
    val budget: Int? = null,
    val homepage: String? = null,
    val imdbId: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = false,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
    var isFavorite: Boolean? = false,
    var genre: String? = null,
) : Parcelable {

    fun getFullBackdropPath() =
        (BuildConfig.LARGE_IMAGE_URL + backdropPath).takeIf { backdropPath.isNullOrBlank().not() }

    fun getFullPosterPath() =
        (BuildConfig.SMALL_IMAGE_URL + posterPath).takeIf { posterPath.isNullOrBlank().not() }
}
