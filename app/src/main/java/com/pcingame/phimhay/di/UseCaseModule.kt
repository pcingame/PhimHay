package com.pcingame.phimhay.di

import com.pcingame.phimhay.domain.usecase.detail.GetCastMovieUseCase
import com.pcingame.phimhay.domain.usecase.detail.GetDetailMovieUseCase
import com.pcingame.phimhay.domain.usecase.detail.GetFavoriteMovieUseCase
import com.pcingame.phimhay.domain.usecase.detail.GetReviewMovieUseCase
import com.pcingame.phimhay.domain.usecase.detail.GetSimilarMovieUseCase
import com.pcingame.phimhay.domain.usecase.detail.RemoveFavoriteMovieUseCase
import com.pcingame.phimhay.domain.usecase.detail.SaveFavoriteMovieUseCase
import com.pcingame.phimhay.domain.usecase.favorite.GetFavoriteMoviesUseCase
import com.pcingame.phimhay.domain.usecase.home.GetTopRatedMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {

    // Home
    single { GetTopRatedMoviesUseCase() }

    // Detail
    single { GetDetailMovieUseCase() }
    single { GetFavoriteMovieUseCase() }
    single { SaveFavoriteMovieUseCase() }
    single { RemoveFavoriteMovieUseCase() }
    single { GetCastMovieUseCase() }
    single { GetSimilarMovieUseCase() }
    single { GetReviewMovieUseCase() }



    // Favorite
    single { GetFavoriteMoviesUseCase() }
}
