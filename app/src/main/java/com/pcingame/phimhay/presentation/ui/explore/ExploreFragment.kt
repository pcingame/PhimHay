package com.pcingame.phimhay.presentation.ui.explore

import com.pcingame.phimhay.base.BaseFragment
import com.pcingame.phimhay.databinding.FragmentExploreBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExploreFragment :
    BaseFragment<FragmentExploreBinding, ExploreViewModel>(FragmentExploreBinding::inflate) {

    override val viewModel: ExploreViewModel by viewModel()
}
