package com.pcingame.phimhay.presentation.ui.detail

import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.pcingame.phimhay.base.BaseFragment
import com.pcingame.phimhay.base.recyclerview.SimpleListAdapter
import com.pcingame.phimhay.databinding.FragmentCastBinding
import com.pcingame.phimhay.databinding.ItemCastBinding
import com.pcingame.phimhay.domain.model.Cast
import com.pcingame.phimhay.domain.model.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel

class CastFragment
    : BaseFragment<FragmentCastBinding, DetailViewModel>(FragmentCastBinding::inflate) {


    override val viewModel: DetailViewModel by viewModel()

    private val listCastMovieAdapter by lazy {
        SimpleListAdapter<ItemCastBinding, Cast>(ItemCastBinding::inflate) { item, _ ->
            Glide.with(imageMovie)
                .load(item.getFullProfilePath())
                .into(imageMovie)
            tvRealName.text = item.name
        }
    }

    override fun setupViews() {
        viewBD.rcvCast.adapter = listCastMovieAdapter
    }

    override fun initData() {
        viewModel.getCastInformation(arguments?.getString(BUNDLE_MOVIE_ID).toString())
    }

    override fun observeData() {
        viewModel.castMovie.observe(viewLifecycleOwner) { cast ->
            listCastMovieAdapter.submitList(cast)
        }
    }

    companion object {
        private const val BUNDLE_MOVIE_ID = "BUNDLE_MOVIE_ID"
        fun newInstance(movieDetail: Movie) = CastFragment().apply {
            arguments = bundleOf(BUNDLE_MOVIE_ID to movieDetail.id)
        }
    }
}
