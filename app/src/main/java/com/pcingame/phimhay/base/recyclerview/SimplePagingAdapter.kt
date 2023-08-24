package com.pcingame.phimhay.base.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.viewbinding.ViewBinding
import com.pcingame.phimhay.base.BaseDiffUtilItemCallback
import com.pcingame.phimhay.common.extension.ViewInflater

class SimplePagingAdapter<ItemBinding : ViewBinding, T : Any>(
    private val onInflateItemBD: ViewInflater<ItemBinding>,
    private val onBind: ItemBinding.(T, Int) -> Unit = { _, _ -> },
) : PagingDataAdapter<T, BaseBindingHolder<ItemBinding, T>>(BaseDiffUtilItemCallback()) {

    var delayClick = 200
    var onItemClick: ItemBinding.(T, Int) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseBindingHolder<ItemBinding, T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemViewBinding = onInflateItemBD(layoutInflater, parent, false)
        return object : BaseBindingHolder<ItemBinding, T>(itemViewBinding) {
            init {
                delayClick = this@SimplePagingAdapter.delayClick
            }

            override fun bindData(itemData: T) {
                super.bindData(itemData)
                itemBD.onBind(itemData, absoluteAdapterPosition)
            }

            override fun onHandleItemClick(mainItem: T) {
                itemBD.onItemClick(mainItem, absoluteAdapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseBindingHolder<ItemBinding, T>, position: Int) {
        val item = getItem(position) ?: return
        holder.bindData(item)
    }
}
