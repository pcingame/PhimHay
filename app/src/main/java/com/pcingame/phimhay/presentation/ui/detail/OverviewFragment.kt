package com.pcingame.phimhay.presentation.ui.detail

import androidx.core.os.bundleOf
import com.pcingame.phimhay.base.BaseFragment
import com.pcingame.phimhay.databinding.FragmentOverviewBinding
import com.pcingame.phimhay.domain.model.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel

class OverviewFragment :
    BaseFragment<FragmentOverviewBinding, DetailViewModel>(FragmentOverviewBinding::inflate) {

    override val viewModel: DetailViewModel by viewModel()

    override fun setupViews() {
        viewBD.tvOverView.text = arguments?.getString(BUNDLE_MOVIE)
    }

    override fun initData() {

    }

    companion object {
        private const val BUNDLE_MOVIE = "BUNDLE_MOVIE"
        fun newInstance(movieDetail: Movie) = OverviewFragment().apply {
            arguments = bundleOf(BUNDLE_MOVIE to movieDetail.overview)
        }
    }
}
