package com.pcingame.phimhay.domain.usecase.detail

import com.pcingame.phimhay.domain.model.Movie
import com.pcingame.phimhay.domain.repository.MovieRepository
import com.pcingame.phimhay.domain.usecase.BaseUseCase
import org.koin.core.component.inject

class GetDetailMovieUseCase : BaseUseCase<Movie, GetDetailMovieUseCase.Input>() {

    private val movieRepository: MovieRepository by inject()

    override suspend fun invoke(input: Input): Movie {
        return movieRepository.getMovie(input.id)
    }

    data class Input(val id: String)
}
