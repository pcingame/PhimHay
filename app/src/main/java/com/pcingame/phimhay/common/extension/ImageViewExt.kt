package com.pcingame.phimhay.common.extension

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

fun ImageView.setTintColor(@ColorRes colorRes: Int) {
    setColorFilter(ContextCompat.getColor(context, colorRes))
}

@SuppressLint("CheckResult")
fun ImageView.load(url: String?, @DrawableRes placeholderRes: Int? = null) {
    url ?: return
    Glide.with(this)
        .load(url)
        .apply {
            if (placeholderRes != null) {
                placeholder(placeholderRes)
            }
        }
        .into(this)
}
