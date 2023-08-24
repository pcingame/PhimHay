package com.pcingame.phimhay.presentation.ui.favorite

import com.pcingame.phimhay.base.BaseViewModel
import com.pcingame.phimhay.common.utils.SingleLiveData
import com.pcingame.phimhay.domain.model.Movie
import com.pcingame.phimhay.domain.usecase.favorite.GetFavoriteMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.inject

class FavoriteViewModel : BaseViewModel() {

    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase by inject()

    val favoriteMovies = SingleLiveData<List<Movie>>()

    fun getFavoriteData() {
        executeTask(onExecute = {
            mLoading.value = true
            val movies = withContext(Dispatchers.IO) {
                getFavoriteMoviesUseCase()
            }
            favoriteMovies.postValue(movies)
            mLoading.value = false
        })
    }
}
