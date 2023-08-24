package com.pcingame.phimhay.data.repository

import com.pcingame.phimhay.data.entity.MovieEntity
import com.pcingame.phimhay.data.local.AppDatabase
import com.pcingame.phimhay.data.remote.MovieApi
import com.pcingame.phimhay.data.remote.response.CastRes
import com.pcingame.phimhay.data.remote.response.GetReviewMovieResponse
import com.pcingame.phimhay.data.remote.response.GetSimilarMovieResponse
import com.pcingame.phimhay.data.remote.response.MovieResponse
import com.pcingame.phimhay.domain.model.Cast
import com.pcingame.phimhay.domain.model.Movie
import com.pcingame.phimhay.domain.model.MovieReview
import com.pcingame.phimhay.domain.model.MovieSimilar
import com.pcingame.phimhay.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val database: AppDatabase,
) : MovieRepository {

    override suspend fun getDiscoverMovie(hashMap: HashMap<String, String>): List<Movie> {
        return movieApi.getDiscoverMovie(hashMap).results
            ?.map(MovieResponse::toModel)
            ?: emptyList()
    }

    override suspend fun getTopRatedMovies(page: Int): List<Movie> {
        return movieApi.getTopRatedMovies(page).results
            ?.map(MovieResponse::toModel)
            ?: emptyList()
    }

    override suspend fun getMovie(movieId: String): Movie {
        return movieApi.getMovie(movieId).toModel()
    }

    override suspend fun saveMovieFavorite(movie: Movie) {
        return database.movieDao().insert(MovieEntity(movie))
    }

    override suspend fun removeMovieFavorite(movieId: String) {
        return database.movieDao().delete(movieId)
    }

    override suspend fun getFavoriteMovie(movieId: String): Movie? {
        return kotlin.runCatching {
            database.movieDao().get(movieId).toModel()
        }.getOrNull()
    }

    override suspend fun getFavoriteMovies(): List<Movie> {
        return kotlin.runCatching {
            database.movieDao().getAll().map(MovieEntity::toModel)
        }.getOrNull() ?: emptyList()
    }

    override suspend fun getMovieCast(movieId: String): List<Cast> {
        return movieApi.getMovieCredits(movieId).cast?.map(CastRes::toModel) ?: emptyList()
    }

    override suspend fun getSimilarMovie(movieId: String, page: Int): List<MovieSimilar> {
        return movieApi.getSimilarMovie(
            movieId,
            page
        ).results?.map(GetSimilarMovieResponse::toModel) ?: emptyList()
    }

    override suspend fun getReviewMovie(movieId: String, page: Int): List<MovieReview> {
        return movieApi.getReviewMovie(movieId, page).results?.map(GetReviewMovieResponse::toModel)
            ?: emptyList()
    }
}
