package com.pcingame.phimhay.base.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pcingame.phimhay.base.BaseDiffUtilItemCallback

/**
 * Bast List Adapter
 */
abstract class BaseListAdapter<T : Any, VH : BaseViewHolder<T>>(
    diffUtilCallback: DiffUtil.ItemCallback<T> = BaseDiffUtilItemCallback()
) : ListAdapter<T, VH>(diffUtilCallback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(getItem(position))
    }

    /**
     * Submit list with DiffUtils
     */
    override fun submitList(list: List<T>?) {
        if (list == currentList) {
            notifyList()
            return
        }
        super.submitList(list ?: emptyList())
    }

    protected fun inflateView(parent: ViewGroup, @LayoutRes layoutResId: Int): View =
        LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)

    @SuppressLint("NotifyDataSetChanged")
    fun notifyList() {
        notifyDataSetChanged()
    }
}
