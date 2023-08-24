package com.pcingame.phimhay.presentation.ui.home

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.bumptech.glide.Glide
import com.pcingame.phimhay.R
import com.pcingame.phimhay.base.BaseFragment
import com.pcingame.phimhay.base.recyclerview.SimplePagingAdapter
import com.pcingame.phimhay.common.extension.getRandomColor
import com.pcingame.phimhay.databinding.FragmentHomeBinding
import com.pcingame.phimhay.databinding.ItemMovieBinding
import com.pcingame.phimhay.domain.model.Movie
import com.pcingame.phimhay.presentation.ui.detail.DetailFragmentArgs
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModel()

    private val topRatedMoviesAdapter by lazy {
        SimplePagingAdapter<ItemMovieBinding, Movie>(ItemMovieBinding::inflate) { item, _ ->
            imageMovie.setBackgroundColor(imageMovie.context.getRandomColor())
            Glide.with(imageMovie)
                .load(item.getFullPosterPath())
                .into(imageMovie)
        }
    }

    override fun setupViews() {
        viewBD.recyclerTopRatedMovies.adapter = topRatedMoviesAdapter.apply {
            onItemClick = { item, _ ->
                val bundle = DetailFragmentArgs(item.id).toBundle()
                navigate(R.id.detailFragment, bundle)
            }
        }
        topRatedMoviesAdapter.addLoadStateListener { state ->
            viewBD.progressLoadMore.isVisible = state.append is LoadState.Loading
        }
    }

    override fun observeData() {
        lifecycleScope.launch {
            viewModel.topRatedMoviesFlow.collectLatest(topRatedMoviesAdapter::submitData)
        }
    }
}
