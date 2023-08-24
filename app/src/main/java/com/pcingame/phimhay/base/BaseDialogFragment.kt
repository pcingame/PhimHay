@file:Suppress("unused")

package com.pcingame.phimhay.base

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.pcingame.phimhay.common.DebugLog
import com.pcingame.phimhay.common.extension.ViewInflater

abstract class BaseDialogFragment<VB : ViewBinding, VM : BaseViewModel>(
    val bindingInflater: ViewInflater<VB>
) : DialogFragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected val debugLog by lazy { DebugLog() }

    protected abstract val viewModel: VM

    /**
     * Override onCreateView function of Fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(layoutInflater, container, false)
        return binding.root
    }

    /**
     * Override onViewCreated function of Fragment
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        initData()
        observeBase()
        debugLog.i(this::class.java.simpleName)
    }

    /**
     * Override onDestroyView function of Fragment
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun setupViews() {
        // Implement in overing function
    }

    protected open fun initData() {
        // Implement in overing function
    }

    private fun observeBase() {
        viewModel.mLoading.observe(viewLifecycleOwner) { shouldLoading ->
            (activity as? BaseActivity<*>)?.showLoading(shouldLoading)
        }
        viewModel.mError.observe(viewLifecycleOwner) { throwable ->
            val message = throwable.message
            val activity = activity as? BaseActivity<*>
            if (activity != null && message != null) {
                activity.showErrorDialog(message)
            }
        }
    }

    protected open fun observeData() {
        // Implement in overing function
    }

    fun show(manager: FragmentManager?) {
        manager?.let {
            show(it, null)
        }
    }

    companion object {
        private const val PERMISSION_REQUESTS = 2
        private fun isPermissionGranted(context: Context, permission: String?): Boolean {
            if (ContextCompat.checkSelfPermission(context, permission!!) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                return true
            }
            return false
        }
    }
}
