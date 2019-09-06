package com.blindmatch.chatex.adapter.delegates

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blindmatch.chatex.adapter.MediaAdapterListener
import com.blindmatch.chatex.model.MediaData

/**
 * Created by divyanshunegi on 15/03/19.
 * Project : blindmatchApp
 */
abstract class BaseChatAdapterDelegate(val vt:Int, private val listener: MediaAdapterListener){

    abstract fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    open fun onBindViewHolder(items:List<MediaData>, position: Int, holder: RecyclerView.ViewHolder){
    }

}