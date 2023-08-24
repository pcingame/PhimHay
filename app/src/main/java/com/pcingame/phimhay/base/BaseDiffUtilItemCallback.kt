package com.pcingame.phimhay.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

open class BaseDiffUtilItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        // Override this if your item have an id
        return oldItem === newItem
    }

    /**
     * 2 ways to make this happened
     *  - Simple way: make your [T] item is data class
     *  - Performance way: make your own [T] item's equals() & hashcode() methods
     */
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) =
        oldItem == newItem
}
