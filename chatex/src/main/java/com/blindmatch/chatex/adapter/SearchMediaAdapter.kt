package com.blindmatch.chatex.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blindmatch.chatex.adapter.delegates.GifAdapterDelegate
import com.blindmatch.chatex.adapter.delegates.UnsupportedAdapterDelegate
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
//    private val imageDelegate =
//        ChatImageAdapterDelegate(ChatType.IMAGE)
//
//    private val leaveDelegate =
//        ChatLeaveAdapterDelegate(
//            ChatType.LEAVE,
//            listener
//        )
//    private val videoDelegate =
//        ChatVideoAdapterDelegate(
//            ChatType.VIDEO,
//            listener
//        )
//
//    private val friendRequestDelegate =
//        ChatFriendRequestAdapterDelegate(
//            ChatType.FRIEND_REQUEST,
//            listener
//        )
//
//    private val friendRequestAcceptedDelegate =
//        ChatFriendRequestAdapterDelegate(
//            ChatType.FRIEND_REQUEST_ACCEPTED,
//            listener
//        )
//
    private val unsupportedDelegate =
        UnsupportedAdapterDelegate(
            MediaType.UNSUPPORTED,
            listener
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            textDelegate.viewType -> textDelegate.onCreateViewHolder(parent)
            else -> unsupportedDelegate.onCreateViewHolder(parent)
        }
    }

    override fun getItemCount() = dataList.size

    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewType = holder.itemViewType
        when (viewType) {
            textDelegate.viewType -> textDelegate.onBindViewHolder(dataList, position, holder)
            else -> unsupportedDelegate.onBindViewHolder(dataList, position, holder)
        }
    }

//    fun update(messages: List<MediaData>?) {
//        if (messages != null) {
//            dataList = messages
//        }
//        notifyDataSetChanged()
//    }
}

interface MediaAdapterListener {
    fun onMediaSelected()
}