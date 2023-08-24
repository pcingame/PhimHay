package com.pcingame.phimhay.presentation.ui.main

import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.pcingame.phimhay.R
import com.pcingame.phimhay.base.BaseActivity
import com.pcingame.phimhay.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
    }

    private val navController by lazy {
        navHostFragment.navController
    }

    private val mainFragmentIdSet =
        setOf(R.id.homeFragment, R.id.exploreFragment, R.id.favoriteFragment)

    private val blockPopBackStackFragments =
        setOf(R.id.homeFragment, R.id.exploreFragment, R.id.favoriteFragment)

    override fun setupViews() {
        supportActionBar?.hide()
        initNavigation()
    }

    private fun initNavigation() {
        val appBarConfiguration = AppBarConfiguration(mainFragmentIdSet)
        setupActionBarWithNavController(navController, appBarConfiguration)
        viewBD.bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isVisible = destination.id in mainFragmentIdSet
            viewBD.bottomNavigation.isVisible = isVisible
        }
        viewBD.bottomNavigation.selectedItemId = R.id.homeFragment
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (navController.currentDestination?.id in blockPopBackStackFragments) return
        super.onBackPressed()
    }
}
