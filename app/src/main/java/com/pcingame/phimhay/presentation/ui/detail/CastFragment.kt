package com.pcingame.phimhay.presentation.ui.detail

import androidx.core.os.bundleOf
import com.pcingame.phimhay.base.BaseFragment
import com.pcingame.phimhay.databinding.FragmentCastBinding
import com.pcingame.phimhay.domain.model.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel

class CastFragment
    : BaseFragment<FragmentCastBinding, DetailViewModel>(FragmentCastBinding::inflate) {


    override val viewModel: DetailViewModel by viewModel()

    companion object {
        private const val BUNDLE_MOVIE_GENRES = "BUNDLE_MOVIE_GENRES"
        fun newInstance(movieDetail: Movie) = CastFragment().apply {
            arguments = bundleOf(BUNDLE_MOVIE_GENRES to movieDetail)
        }
    }
}
