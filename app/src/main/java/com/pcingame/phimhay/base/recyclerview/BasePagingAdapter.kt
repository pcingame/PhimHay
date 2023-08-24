package com.pcingame.phimhay.base.recyclerview

import androidx.paging.PagingDataAdapter
import com.pcingame.phimhay.base.BaseDiffUtilItemCallback

/**
 * Bast List Adapter
 */
abstract class BasePagingAdapter<T : Any, VH : BaseViewHolder<T>>
    : PagingDataAdapter<T, VH>(BaseDiffUtilItemCallback()) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position) ?: return
        holder.bindData(item)
    }
}
