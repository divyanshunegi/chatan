package com.blindmatch.chatex

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.options_view.view.*


/**
 * Created by divyanshunegi on 2019-09-06.
 * Project : Chatan
 */
class ChatExView (context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs){

    init {
        this.visibility = View.GONE
        val inflater = LayoutInflater.from(getContext())
        inflater.inflate(R.layout.options_view, this)
        youtube_select.setOnClickListener {
            Chatex.showYoutubeSearch()
            Toast.makeText(getContext(), "youtube",Toast.LENGTH_SHORT).show()
        }

        gif_select.setOnClickListener {
            Chatex.showGifSearch()
            Toast.makeText(getContext(), "GIF",Toast.LENGTH_SHORT).show()
        }

        sticker_select.setOnClickListener {
            Chatex.showStickerSearch()
            Toast.makeText(getContext(), "Sticker",Toast.LENGTH_SHORT).show()
        }

    }

}