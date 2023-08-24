package com.pcingame.phimhay.domain.usecase.detail

import com.pcingame.phimhay.domain.model.Cast
import com.pcingame.phimhay.domain.repository.MovieRepository
import com.pcingame.phimhay.domain.usecase.BaseUseCase
import org.koin.core.component.inject

class GetCastMovieUseCase : BaseUseCase<List<Cast>, GetCastMovieUseCase.Input>() {
    private val movieRepository: MovieRepository by inject()

    override suspend fun invoke(input: Input): List<Cast> {
        val movieId = input.id
        return movieRepository.getMovieCast(movieId)
    }

    data class Input(val id: String)
}
