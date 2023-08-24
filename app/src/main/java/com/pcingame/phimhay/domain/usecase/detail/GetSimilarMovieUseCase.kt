package com.pcingame.phimhay.domain.usecase.detail

import com.pcingame.phimhay.domain.model.MovieSimilar
import com.pcingame.phimhay.domain.repository.MovieRepository
import com.pcingame.phimhay.domain.usecase.BaseUseCase
import org.koin.core.component.inject

class GetSimilarMovieUseCase : BaseUseCase<List<MovieSimilar>, GetSimilarMovieUseCase.Input>() {
    private val movieRepository: MovieRepository by inject()

    override suspend fun invoke(input: Input): List<MovieSimilar> {
        val movieId = input.id
        val page = input.page
        return movieRepository.getSimilarMovie(movieId, page)
    }

    data class Input(val id: String, val page: Int)
}