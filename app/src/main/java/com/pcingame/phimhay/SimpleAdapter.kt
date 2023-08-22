package com.pcingame.phimhay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SimpleAdapter(
    private val layoutManager: GridLayoutManager? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        SMALL,
        DETAIL
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.DETAIL.ordinal
        else ViewType.SMALL.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.DETAIL.ordinal -> DetailViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.detail_item, parent, false)
            )

            else -> SimpleViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.simple_item, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }


}