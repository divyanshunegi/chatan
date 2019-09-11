package com.blindmatch.chatex.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blindmatch.chatex.adapter.delegates.GifAdapterDelegate
import com.blindmatch.chatex.adapter.delegates.StickerAdapterDelegate
import com.blindmatch.chatex.adapter.delegates.UnsupportedAdapterDelegate
import com.blindmatch.chatex.adapter.delegates.YoutubeAdapterDelegate
import com.blindmatch.chatex.model.MediaData
import com.blindmatch.chatex.model.MediaType


/**
 * Created by divyanshunegi on 02/03/19.
 * Project : blindmatchApp
 */
class SearchMediaAdapter(private var dataList: List<MediaData> = emptyList(), val listener: MediaAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val textDelegate =
        GifAdapterDelegate(
            MediaType.GIF,
            listener
        )

    private val youtubeDelegate =
        YoutubeAdapterDelegate(
            MediaType.YOUTUBE,
            listener
        )

    private val unsupportedDelegate =
        UnsupportedAdapterDelegate(
            MediaType.UNSUPPORTED,
            listener
        )

    private val stickerAdapterDelegate =
        StickerAdapterDelegate(
            MediaType.STICKER,
            listener
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            textDelegate.viewType -> textDelegate.onCreateViewHolder(parent)
            youtubeDelegate.viewType -> youtubeDelegate.onCreateViewHolder(parent)
            stickerAdapterDelegate.viewType -> stickerAdapterDelegate.onCreateViewHolder(parent)
            else -> unsupportedDelegate.onCreateViewHolder(parent)
        }
    }

    override fun getItemCount() = dataList.size

    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = holder.itemViewType
        holder.itemView.setOnClickListener {
            listener.onMediaSelected(dataList[position])
        }
        when (viewType) {
            textDelegate.viewType -> textDelegate.onBindViewHolder(dataList, position, holder)
            youtubeDelegate.viewType -> youtubeDelegate.onBindViewHolder(dataList, position, holder)
            stickerAdapterDelegate.viewType -> stickerAdapterDelegate.onBindViewHolder(dataList, position, holder)
            else -> unsupportedDelegate.onBindViewHolder(dataList, position, holder)
        }
    }

}

interface MediaAdapterListener {
    fun onMediaSelected(mediaData: MediaData)
}