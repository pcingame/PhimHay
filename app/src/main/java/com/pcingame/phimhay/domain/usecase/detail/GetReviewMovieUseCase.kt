package com.pcingame.phimhay.domain.usecase.detail

import com.pcingame.phimhay.domain.model.MovieReview
import com.pcingame.phimhay.domain.repository.MovieRepository
import com.pcingame.phimhay.domain.usecase.BaseUseCase
import org.koin.core.component.inject

class GetReviewMovieUseCase : BaseUseCase<List<MovieReview>, GetReviewMovieUseCase.Input>() {
    private val movieRepository: MovieRepository by inject()

    override suspend fun invoke(input: Input): List<MovieReview> {
        val movieId = input.id
        val page = input.page
        return movieRepository.getReviewMovie(movieId, page)
    }

    data class Input(val id: String, val page: Int)
}