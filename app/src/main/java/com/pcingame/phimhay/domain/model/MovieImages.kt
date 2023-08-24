package com.pcingame.phimhay.domain.model

data class MovieImages(
    var backdrop: Backdrop = Backdrop(),
    var poster: Poster = Poster(),
)

data class Backdrop(
    val aspectRatio: Double? = null,
    val filePath: String? = null,
    val height: Int? = null,
    val iso6391: String? = null,
    val voteAverage: Int? = null,
    val voteCount: Int? = null,
    val width: Int? = null
)

data class Poster(
    val aspectRatio: Double? = null,
    val filePath: String? = null,
    val height: Int? = null,
    val iso6391: String? = null,
    val voteAverage: Int? = null,
    val voteCount: Int? = null,
    val width: Int? = null
)
