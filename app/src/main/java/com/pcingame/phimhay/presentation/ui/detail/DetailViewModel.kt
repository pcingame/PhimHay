package com.pcingame.phimhay.presentation.ui.detail

import com.pcingame.phimhay.base.BaseViewModel
import com.pcingame.phimhay.common.utils.SingleLiveData
import com.pcingame.phimhay.domain.model.Movie
import com.pcingame.phimhay.domain.usecase.detail.GetDetailMovieUseCase
import com.pcingame.phimhay.domain.usecase.detail.GetFavoriteMovieUseCase
import com.pcingame.phimhay.domain.usecase.detail.RemoveFavoriteMovieUseCase
import com.pcingame.phimhay.domain.usecase.detail.SaveFavoriteMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.inject

class DetailViewModel : BaseViewModel() {
    private val getDetailMoviesUseCase: GetDetailMovieUseCase by inject()
    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase by inject()
    private val saveFavoriteMovieUseCase: SaveFavoriteMovieUseCase by inject()
    private val removeFavoriteMovieUseCase: RemoveFavoriteMovieUseCase by inject()

    val detailMovie = SingleLiveData<Movie>()
    val favorite = SingleLiveData<Boolean>()

    fun getMovieInformation(movieId: String) {
        val param = GetDetailMovieUseCase.Input(movieId)
        executeTask {
            mLoading.value = true
            val movie = withContext(Dispatchers.IO) {
                getDetailMoviesUseCase(param)
            }
            detailMovie.value = movie
            favorite.value = withContext(Dispatchers.IO) {
                getFavoriteMovieUseCase(movieId)
            } != null

            mLoading.value = false
        }
    }

    fun markFavoriteOrNot() {
        val movie = detailMovie.value ?: return
        executeTask {
            if (favorite.value == true) {
                removeFavoriteMovieUseCase(movie.id)
            } else {
                saveFavoriteMovieUseCase(movie)
            }
            favorite.value = favorite.value != true
        }
    }
}
