package com.example.mobiquitytask.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object BindingUtils {
    fun setImageUrl(
        imageView: ImageView,
        url: String?,
        placeHolder: Int
    ) {
        val context = imageView.context
        Glide.with(context)
            .load(url)
            .placeholder(placeHolder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }

    fun setImageDrawable(imageView: ImageView, imageID: Int) {
        val context = imageView.context
        Glide.with(context)
            .load(imageID)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }
}