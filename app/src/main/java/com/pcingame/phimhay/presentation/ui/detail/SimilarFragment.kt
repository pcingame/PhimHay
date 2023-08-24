package com.pcingame.phimhay.presentation.ui.detail

import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.pcingame.phimhay.base.BaseFragment
import com.pcingame.phimhay.base.recyclerview.SimpleListAdapter
import com.pcingame.phimhay.databinding.FragmentSimilarBinding
import com.pcingame.phimhay.databinding.ItemMovieBinding
import com.pcingame.phimhay.domain.model.Movie
import com.pcingame.phimhay.domain.model.MovieSimilar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimilarFragment :
    BaseFragment<FragmentSimilarBinding, DetailViewModel>(FragmentSimilarBinding::inflate) {

    override val viewModel: DetailViewModel by viewModel()

    private val listSimilarMovieAdapter by lazy {
        SimpleListAdapter<ItemMovieBinding, MovieSimilar>(ItemMovieBinding::inflate) { item, _ ->
            Glide.with(imageMovie)
                .load(item.getFullPosterPath())
                .into(imageMovie)
        }
    }

    override fun setupViews() {
        viewBD.recyclerSimilarMovie.adapter = listSimilarMovieAdapter
    }

    override fun initData() {
        viewModel.getSimilarMovieList(arguments?.getString(BUNDLE_MOVIE_ID).toString(), 1)
    }

    override fun observeData() {
        viewModel.similarMovieList.observe(viewLifecycleOwner) { movieList ->
            listSimilarMovieAdapter.submitList(movieList)
        }
    }

    companion object {
        private const val BUNDLE_MOVIE_ID = "BUNDLE_MOVIE_ID"
        fun newInstance(movieDetail: Movie) = SimilarFragment().apply {
            arguments = bundleOf(
                BUNDLE_MOVIE_ID to movieDetail.id,
            )
        }
    }
}
