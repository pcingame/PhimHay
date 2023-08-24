package com.pcingame.phimhay.domain.usecase.detail

import com.pcingame.phimhay.domain.model.Movie
import com.pcingame.phimhay.domain.repository.MovieRepository
import com.pcingame.phimhay.domain.usecase.BaseUseCase
import org.koin.core.component.inject

class GetFavoriteMovieUseCase : BaseUseCase<Movie?, String>() {

    private val movieRepository: MovieRepository by inject()

    override suspend fun invoke(input: String): Movie? {
        return movieRepository.getFavoriteMovie(input)
    }
}
