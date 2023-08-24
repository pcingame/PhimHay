package com.pcingame.phimhay.base.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.pcingame.phimhay.common.extension.ViewInflater
import com.pcingame.phimhay.common.extension.click

/**
 * Simple List Adapter
 * Bind simple data with Single View Holder type
 * @sample
 * <pre>
 * val adapter = SimpleListAdapter<ItemProfileBinding, Profile>(ItemProfileBinding::inflate) { profile, position ->
 *      textFirstName.text = profile.firstName
 *      textLastName.text = profile.lastName
 *      viewLineBottom.isInvisible = position == profiles.size -1 // not show bottom line if item is last
 * }
 * adapter.onItemClick = { profile, position ->
 *     // handle click item event
 * }
 * recyclerView.adapter = adapter // set adapter
 * adapter.submitList(profiles) // update data
 *
 * </pre>
 * @param onInflateItemBD : inflate Layout resource id of item
 * @param onBind : High-order function, call when bind data into itemView
 * @param T: type of item's data
 * @author huytq
 */

open class SimpleListAdapter<ItemBD : ViewBinding, T : Any>(
    private val onInflateItemBD: ViewInflater<ItemBD>,
    var onBind: ItemBD.(T, Int) -> Unit = { _, _ -> },
) : BaseListAdapter<T, BaseBindingHolder<ItemBD, T>>() {
    var delayClick = 200
    var onItemClick: ItemBD.(T, Int) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingHolder<ItemBD, T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = onInflateItemBD(layoutInflater, parent, false)
        return object : BaseBindingHolder<ItemBD, T>(viewBinding) {

            init {
                delayClick = this@SimpleListAdapter.delayClick
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
}

open class SimpleBDAdapter<ItemBD : ViewBinding, T : Any>(
    private val onInflateItemBD: (LayoutInflater, ViewGroup?, Boolean) -> ItemBD,
    var onBind: (ItemBD, T, Int) -> Unit = { _, _, _ -> },
) : BaseListAdapter<T, BaseViewHolder<T>>() {
    var delayClick = 500
    var onItemClick: (ItemBD, T, Int) -> Unit = { _, _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBD = onInflateItemBD(layoutInflater, parent, false)
        return object : BaseViewHolder<T>(itemBD.root) {
            init {
                itemView.click(delayClick) {
                    itemData?.let {
                        onItemClick(itemBD, it, absoluteAdapterPosition)
                    }
                }
            }

            override fun bindData(itemData: T) {
                super.bindData(itemData)
                onBind(itemBD, itemData, absoluteAdapterPosition)
            }
        }
    }
}
