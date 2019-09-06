package com.blindmatch.chatex

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.blindmatch.chatex.adapter.MediaAdapterListener
import com.blindmatch.chatex.adapter.SearchMediaAdapter
import com.blindmatch.chatex.model.MediaData
import com.blindmatch.chatex.model.MediaType
import kotlinx.android.synthetic.main.options_view.view.*


/**
 * Created by divyanshunegi on 2019-09-06.
 * Project : Chatan
 */
class ChatExView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs),
    MediaAdapterListener {

    override fun onMediaSelected() {

    }

    private lateinit var adapter: SearchMediaAdapter
    private lateinit var manager: LinearLayoutManager
    private val dataList: MutableList<MediaData> = mutableListOf()

    init {
        this.visibility = View.GONE
        val inflater = LayoutInflater.from(getContext())
        inflater.inflate(R.layout.options_view, this)
        youtube_select.setOnClickListener {
            Chatex.showYoutubeSearch()
            Toast.makeText(getContext(), "youtube", Toast.LENGTH_SHORT).show()
        }

        gif_select.setOnClickListener {
            Chatex.showGifSearch()
            Toast.makeText(getContext(), "GIF", Toast.LENGTH_SHORT).show()
        }

        sticker_select.setOnClickListener {
            Chatex.showStickerSearch()
            Toast.makeText(getContext(), "Sticker", Toast.LENGTH_SHORT).show()
        }

        setupSearchListView()
    }

    private fun setupSearchListView() {
        dataList.add(MediaData(mediaLink = "https://media.giphy.com/media/APpQYfZAYy7OIYmu5w/giphy.gif", type = MediaType.GIF))
        dataList.add(MediaData(mediaLink = "https://media.giphy.com/media/3kw9T9x49IxbUoQYE4/giphy.gif", type = MediaType.GIF))
        dataList.add(MediaData(mediaLink = "https://media.giphy.com/media/APpQYfZAYy7OIYmu5w/giphy.gif", type = MediaType.GIF))
        dataList.add(MediaData(mediaLink = "https://media.giphy.com/media/3kw9T9x49IxbUoQYE4/giphy.gif", type = MediaType.GIF))
        dataList.add(MediaData(mediaLink = "https://media.giphy.com/media/APpQYfZAYy7OIYmu5w/giphy.gif", type = MediaType.GIF))
        manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = SearchMediaAdapter(dataList, this)
        productsListView.layoutManager = manager
        productsListView.adapter = adapter
    }

}