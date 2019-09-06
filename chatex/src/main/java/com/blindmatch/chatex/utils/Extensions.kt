package com.blindmatch.chatex.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide

/**
 * Created by divyanshunegi on 2019-09-07.
 * Project : Chatan
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.loadChatexImage(url: String?) {
    if (url != null) {
        Glide.with(context)
            .load(url)
            .into(this)

    }
}