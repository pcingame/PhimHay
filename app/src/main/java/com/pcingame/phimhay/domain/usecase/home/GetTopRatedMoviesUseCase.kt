package com.pcingame.phimhay.domain.usecase.home

import com.pcingame.phimhay.domain.model.Movie
import com.pcingame.phimhay.domain.repository.MovieRepository
import com.pcingame.phimhay.domain.usecase.BaseUseCase
import org.koin.core.component.inject

class GetTopRatedMoviesUseCase : BaseUseCase<List<Movie>, GetTopRatedMoviesUseCase.Input>() {

    private val movieRepository: MovieRepository by inject()

    override suspend fun invoke(input: Input): List<Movie> {
        val page = input.page
        return movieRepository.getTopRatedMovies(page)
    }

    data class Input(val page: Int)
}
