package com.pcingame.phimhay.presentation.ui.detail

import com.pcingame.phimhay.base.BaseViewModel
import com.pcingame.phimhay.common.utils.SingleLiveData
import com.pcingame.phimhay.domain.model.Cast
import com.pcingame.phimhay.domain.model.Movie
import com.pcingame.phimhay.domain.model.MovieReview
import com.pcingame.phimhay.domain.model.MovieSimilar
import com.pcingame.phimhay.domain.usecase.detail.GetCastMovieUseCase
import com.pcingame.phimhay.domain.usecase.detail.GetDetailMovieUseCase
import com.pcingame.phimhay.domain.usecase.detail.GetFavoriteMovieUseCase
import com.pcingame.phimhay.domain.usecase.detail.GetReviewMovieUseCase
import com.pcingame.phimhay.domain.usecase.detail.GetSimilarMovieUseCase
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
    private val getCastMovieUseCase: GetCastMovieUseCase by inject()
    private val getSimilarMovieUseCase: GetSimilarMovieUseCase by inject()
    private val getReviewMovieUseCase: GetReviewMovieUseCase by inject()

    val detailMovie = SingleLiveData<Movie>()
    val favorite = SingleLiveData<Boolean>()
    val castMovie = SingleLiveData<List<Cast>>()
    val similarMovieList = SingleLiveData<List<MovieSimilar>>()
    val reviewMovieList = SingleLiveData<List<MovieReview>>()

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

    fun getCastInformation(movieId: String) {
        val params = GetCastMovieUseCase.Input(movieId)
        executeTask(
            onExecute = {
                mLoading.value = true
                val cast = withContext(Dispatchers.IO) {
                    getCastMovieUseCase(params)
                }
                castMovie.value = cast
                mLoading.value = false
            }
        )
    }

    fun getSimilarMovieList(movieId: String, page: Int) {
        val params = GetSimilarMovieUseCase.Input(movieId, page)
        executeTask(onExecute = {
            mLoading.value = true
            val similarMovie = withContext(Dispatchers.IO) {
                getSimilarMovieUseCase(params)
            }
            similarMovieList.value = similarMovie
            mLoading.value = false
        })
    }

    fun getReviewMovieList(movieId: String, page: Int) {
        val params = GetReviewMovieUseCase.Input(movieId, page)
        executeTask(onExecute = {
            mLoading.value = true
            val reviewMovie = withContext(Dispatchers.IO) {
                getReviewMovieUseCase(params)
            }
            reviewMovieList.value = reviewMovie
            mLoading.value = false
        })
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
