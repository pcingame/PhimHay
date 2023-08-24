package com.pcingame.phimhay.presentation.ui.favorite

import com.bumptech.glide.Glide
import com.pcingame.phimhay.R
import com.pcingame.phimhay.base.BaseFragment
import com.pcingame.phimhay.base.recyclerview.SimpleListAdapter
import com.pcingame.phimhay.databinding.FragmentFavoriteBinding
import com.pcingame.phimhay.databinding.ItemMovieBinding
import com.pcingame.phimhay.domain.model.Movie
import com.pcingame.phimhay.presentation.ui.detail.DetailFragmentArgs
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment :
    BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(FragmentFavoriteBinding::inflate) {

    override val viewModel: FavoriteViewModel by viewModel()

    private val moviesAdapter by lazy {
        SimpleListAdapter<ItemMovieBinding, Movie>(ItemMovieBinding::inflate) { item, _ ->
            Glide.with(imageMovie)
                .load(item.getFullPosterPath())
                .into(imageMovie)
        }
    }

    override fun setupViews() {
        viewBD.recyclerFavoriteMovies.adapter = moviesAdapter.apply {
            onItemClick = { item, _ ->
                val bundle = DetailFragmentArgs(item.id).toBundle()
                navigate(R.id.detailFragment, bundle)
            }
        }

    }

    override fun initData() {
        viewModel.getFavoriteData()
    }

    override fun observeData() {
        viewModel.favoriteMovies.observe(viewLifecycleOwner) { movies ->
            moviesAdapter.submitList(movies)
        }
    }
}
