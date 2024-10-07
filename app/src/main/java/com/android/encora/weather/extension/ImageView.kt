package com.android.encora.weather.extension

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/*
This is an extension for all AppCompatImageView for loading URL strings
*/
fun AppCompatImageView.loadUrl(imageUrl: String?) {
    if (imageUrl == null || imageUrl.isEmpty()) return

    Glide.with(context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .apply(RequestOptions().transforms(RoundedCorners(24)))
        .into(this)
}