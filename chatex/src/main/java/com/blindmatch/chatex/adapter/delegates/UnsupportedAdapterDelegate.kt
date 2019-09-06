package com.blindmatch.chatex.adapter.delegates

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blindmatch.chatex.R
import com.blindmatch.chatex.adapter.MediaAdapterListener
import com.blindmatch.chatex.model.MediaData
import com.blindmatch.chatex.utils.inflate

/**
 * Created by divyanshunegi on 02/03/19.
 * Project : blindmatchApp
 */
class UnsupportedAdapterDelegate(val viewType: Int, listener: MediaAdapterListener) :
    BaseChatAdapterDelegate(viewType, listener) {

    //this is in case we want to prodive an entirely different data model to the element
    fun isForViewType(items: List<*>, position: Int): Boolean {
        return items[position] is MediaData
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return UnsupportedViewHolder(
            parent.inflate(
                R.layout.element_unsupported
            )
        )
    }


    override fun onBindViewHolder(
        items: List<MediaData>,
        position: Int,
        holder: RecyclerView.ViewHolder
    ) {
        super.onBindViewHolder(items, position, holder)
    }

}

class UnsupportedViewHolder(iv: View):RecyclerView.ViewHolder(iv)