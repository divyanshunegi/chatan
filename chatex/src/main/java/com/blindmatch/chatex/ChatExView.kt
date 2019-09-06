package com.blindmatch.chatex

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout



/**
 * Created by divyanshunegi on 2019-09-06.
 * Project : Chatan
 */
class ChatExView (context: Context, attrs: AttributeSet) : FrameLayout(context, attrs){

    init {
        this.visibility = View.GONE
        val inflater = LayoutInflater.from(getContext())
        inflater.inflate(R.layout.options_view, this)
    }

}