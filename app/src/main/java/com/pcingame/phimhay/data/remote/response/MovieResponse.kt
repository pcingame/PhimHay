package com.pcingame.phimhay.data.remote.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.pcingame.phimhay.BuildConfig
import com.pcingame.phimhay.domain.mapper.MapAbleToModel
import com.pcingame.phimhay.domain.model.Movie

@Entity(tableName = "movies")
data class MovieResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("adult")
    val adult: Boolean? = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("budget")
    val budget: Int? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("revenue")
    val revenue: Int? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = false,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("genres") var genres: List<Genres>? = null
) : MapAbleToModel<Movie> {

    fun getFullBackdropPath() =
        if (backdropPath.isNullOrBlank()) null else BuildConfig.SMALL_IMAGE_URL + backdropPath

    fun getFullPosterPath() =
        if (posterPath.isNullOrBlank()) null else BuildConfig.SMALL_IMAGE_URL + posterPath

    override fun toModel(): Movie {
        return Movie(
            id = id,
            adult = adult,
            backdropPath = backdropPath,
            budget = budget,
            homepage = homepage,
            imdbId = imdbId,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            revenue = revenue,
            runtime = runtime,
            status = status,
            tagline = tagline,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
            genre = genres?.first()?.name
        )
    }
}

data class Genres(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null
)
