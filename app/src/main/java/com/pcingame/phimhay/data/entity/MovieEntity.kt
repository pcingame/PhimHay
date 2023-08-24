package com.pcingame.phimhay.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pcingame.phimhay.domain.mapper.MapAbleToModel
import com.pcingame.phimhay.domain.model.Movie


@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "adult")
    val adult: Boolean? = false,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,
    @ColumnInfo(name = "budget")
    val budget: Int? = null,
    @ColumnInfo(name = "homepage")
    val homepage: String? = null,
    @ColumnInfo(name = "imdb_id")
    val imdbId: String? = null,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String? = null,
    @ColumnInfo(name = "original_title")
    val originalTitle: String? = null,
    @ColumnInfo(name = "overview")
    val overview: String? = null,
    @ColumnInfo(name = "popularity")
    val popularity: Double? = null,
    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,
    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,
    @ColumnInfo(name = "revenue")
    val revenue: Int? = null,
    @ColumnInfo(name = "runtime")
    val runtime: Int? = null,
    @ColumnInfo(name = "status")
    val status: String? = null,
    @ColumnInfo(name = "tagline")
    val tagline: String? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "video")
    val video: Boolean? = false,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double? = null,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int? = null,
    @ColumnInfo(name = "genres")
    val genres: String? = null,
) : MapAbleToModel<Movie> {

    constructor(movie: Movie) : this(
        id = movie.id,
        adult = movie.adult,
        backdropPath = movie.backdropPath,
        budget = movie.budget,
        homepage = movie.homepage,
        imdbId = movie.imdbId,
        originalLanguage = movie.originalLanguage,
        originalTitle = movie.originalTitle,
        overview = movie.overview,
        popularity = movie.popularity,
        posterPath = movie.posterPath,
        releaseDate = movie.releaseDate,
        revenue = movie.revenue,
        runtime = movie.runtime,
        status = movie.status,
        tagline = movie.tagline,
        title = movie.title,
        video = movie.video,
        voteAverage = movie.voteAverage,
        voteCount = movie.voteCount,
        genres = movie.genre
    )

    override fun toModel() = Movie(
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
        genre = genres
    )
}
