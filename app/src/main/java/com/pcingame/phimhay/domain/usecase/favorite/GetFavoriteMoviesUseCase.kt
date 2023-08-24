package com.pcingame.phimhay.domain.usecase.favorite

import com.pcingame.phimhay.domain.model.Movie
import com.pcingame.phimhay.domain.repository.MovieRepository
import com.pcingame.phimhay.domain.usecase.NoParamUseCase
import org.koin.core.component.inject

class GetFavoriteMoviesUseCase : NoParamUseCase<List<Movie>>() {

    private val movieRepository: MovieRepository by inject()

    override suspend fun invoke(): List<Movie> {
        return movieRepository.getFavoriteMovies()
    }
}
