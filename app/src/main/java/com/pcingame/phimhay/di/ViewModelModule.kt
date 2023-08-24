package com.pcingame.phimhay.di

import com.pcingame.phimhay.presentation.ui.detail.DetailViewModel
import com.pcingame.phimhay.presentation.ui.explore.ExploreViewModel
import com.pcingame.phimhay.presentation.ui.favorite.FavoriteViewModel
import com.pcingame.phimhay.presentation.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { ExploreViewModel() }
    viewModel { FavoriteViewModel() }
    viewModel { DetailViewModel() }
}
