package com.pcingame.phimhay.presentation.ui.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.pcingame.phimhay.base.BaseViewModel
import com.pcingame.phimhay.base.paging.BasePagingSource
import com.pcingame.phimhay.domain.usecase.home.GetTopRatedMoviesUseCase
import org.koin.core.component.inject

class HomeViewModel : BaseViewModel() {
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase by inject()

    val topRatedMoviesFlow by lazy {
        Pager(PagingConfig(20)) {
            BasePagingSource { page ->
                getTopRatedMoviesUseCase(GetTopRatedMoviesUseCase.Input(page))
            }
        }.flow.cachedIn(scope)
    }
}
