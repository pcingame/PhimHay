package com.pcingame.phimhay.presentation.ui.detail

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.pcingame.phimhay.base.BaseFragment
import com.pcingame.phimhay.databinding.FragmentReviewBinding
import com.pcingame.phimhay.domain.model.Movie

class ReviewFragment :
    BaseFragment<FragmentReviewBinding, DetailViewModel>(FragmentReviewBinding::inflate) {

    override val viewModel: DetailViewModel by viewModels()

    companion object {
        private const val BUNDLE_MOVIE_ID = "BUNDLE_MOVIE_ID"
        private const val BUNDLE_MOVIE_TITLE = "BUNDLE_MOVIE_TITLE"
        fun newInstance(movieDetail: Movie) = ReviewFragment().apply {
            arguments = bundleOf(
                BUNDLE_MOVIE_ID to movieDetail.id,
                BUNDLE_MOVIE_TITLE to movieDetail.title
            )
        }
    }
}
