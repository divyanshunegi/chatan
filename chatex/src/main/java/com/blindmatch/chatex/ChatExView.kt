package com.blindmatch.chatex

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.blindmatch.chatex.adapter.MediaAdapterListener
import com.blindmatch.chatex.adapter.SearchMediaAdapter
import com.blindmatch.chatex.api.ChatexService
import com.blindmatch.chatex.model.MediaData
import com.blindmatch.chatex.model.MediaType
import kotlinx.android.synthetic.main.options_view.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * Created by divyanshunegi on 2019-09-06.
 * Project : Chatan
 */
class ChatExView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs),
    MediaAdapterListener {


    private var selected: ButtonType = ButtonType.YOUTUBE
    private var isTyping = false
    private var searchQuery = ""
    private var textTimer = Handler()
    private var timerTask = Runnable {
        progressBar.visibility = View.VISIBLE
        when (selected) {
            ButtonType.YOUTUBE -> searchInYoutube()
            ButtonType.GIF -> searchInGif()
            ButtonType.STICKER -> searchInSticker()
        }
    }

    private fun searchInSticker() {

        if (!searchQuery.isNotEmpty()) {
            progressBar.visibility = View.INVISIBLE
            return
        }
        dataList.clear()
        adapter.notifyDataSetChanged()
        productsListView.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val giphyData =
                    ChatexService.getGiphyClient("stickers").searchGiphy(query = searchQuery)
                        .await()
                searchQuery = ""
                dataList.clear()
                for (giph in giphyData.data) {
                    dataList.add(
                        MediaData(
                            title = giph.title,
                            mediaLink = giph.images.previewGif.url,
                            type = MediaType.GIF
                        )
                    )
                }
                GlobalScope.launch(Dispatchers.Main) {
                    progressBar.visibility = View.INVISIBLE
                    adapter.notifyDataSetChanged()
                }
            } catch (ex: Exception) {
                progressBar.visibility = View.INVISIBLE
                Log.e("Chatex", ex.toString())
            }
        }
    }

    private fun searchInGif() {
        if (!searchQuery.isNotEmpty()) {
            progressBar.visibility = View.INVISIBLE
            return
        }
        dataList.clear()
        adapter.notifyDataSetChanged()
        productsListView.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val giphyData =
                    ChatexService.getGiphyClient().searchGiphy(query = searchQuery).await()
                searchQuery = ""
                dataList.clear()
                for (giph in giphyData.data) {
                    dataList.add(
                        MediaData(
                            title = giph.title,
                            mediaLink = giph.images.previewGif.url,
                            type = MediaType.GIF
                        )
                    )
                }
                GlobalScope.launch(Dispatchers.Main) {
                    progressBar.visibility = View.INVISIBLE
                    adapter.notifyDataSetChanged()
                }
            } catch (ex: Exception) {
                progressBar.visibility = View.INVISIBLE
                Log.e("Chatex", ex.toString())
            }
        }

    }

    private fun searchInYoutube() {
        if (!searchQuery.isNotEmpty()) {
            progressBar.visibility = View.INVISIBLE
            return
        }
        dataList.clear()
        adapter.notifyDataSetChanged()
        productsListView.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val result =
                    ChatexService.getYoutubeClient().getVideosByKeyword(query = searchQuery).await()
                searchQuery = ""
                dataList.clear()
                result.items.forEach {
                    dataList.add(
                        MediaData(
                            title = it.snippet?.title,
                            mediaLink = it.snippet?.thumbnails?.high?.url,
                            type = MediaType.YOUTUBE,
                            videoId = it.id.videoId
                        )
                    )
                }
                GlobalScope.launch(Dispatchers.Main) {
                    progressBar.visibility = View.INVISIBLE
                    adapter.notifyDataSetChanged()
                }
            } catch (ex: Exception) {
                progressBar.visibility = View.INVISIBLE
                Log.e("Chatex", ex.toString())
            }
        }
    }

    override fun onMediaSelected(mediaData: MediaData) {
        ic_cancel_button.performClick()
        Chatex.toggleView()
        Chatex.mediaSelected(mediaData)
    }

    private lateinit var adapter: SearchMediaAdapter
    private lateinit var manager: LinearLayoutManager
    private val dataList: MutableList<MediaData> = mutableListOf()

    init {
        this.visibility = View.GONE
        val inflater = LayoutInflater.from(getContext())
        inflater.inflate(R.layout.options_view, this)
        ic_cancel_button.setOnClickListener {
            buttonLayout.visibility = View.VISIBLE
            searchView.visibility = View.GONE
            productsListView.visibility = View.GONE
            search_icon.visibility = View.GONE
            it.visibility = View.GONE
            dataList.clear()
            searchQuery = ""
            searchView.setText("")
            hidekeyboard()
        }

        youtube_select.setOnClickListener {
            selected = ButtonType.YOUTUBE
            buttonLayout.visibility = View.GONE
            searchView.visibility = View.VISIBLE
            productsListView.visibility = View.VISIBLE
            search_icon.visibility = View.VISIBLE
            ic_cancel_button.visibility = View.VISIBLE
            setSearchHint()
            dataList.clear()
        }

        gif_select.setOnClickListener {
            selected = ButtonType.GIF
            buttonLayout.visibility = View.GONE
            searchView.visibility = View.VISIBLE
            productsListView.visibility = View.VISIBLE
            search_icon.visibility = View.VISIBLE
            ic_cancel_button.visibility = View.VISIBLE
            setSearchHint()
            dataList.clear()
        }

        sticker_select.setOnClickListener {
            selected = ButtonType.STICKER
            buttonLayout.visibility = View.GONE
            searchView.visibility = View.VISIBLE
            productsListView.visibility = View.VISIBLE
            search_icon.visibility = View.VISIBLE
            ic_cancel_button.visibility = View.VISIBLE
            setSearchHint()
            dataList.clear()
        }

        setupSearchListView()
        addTextWatcherOnSearch()
    }

    private fun hidekeyboard() {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun addTextWatcherOnSearch() {
        searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchQuery = p0.toString()
                textTimer.removeCallbacks(timerTask)
                textTimer.postDelayed(timerTask, 1500)
            }
        })
    }

    private fun setSearchHint() {
        when (selected) {
            ButtonType.YOUTUBE -> searchView.hint = "Search Youtube video..."
            ButtonType.GIF -> searchView.hint = "Search Gifs, Powered By GIPHY"
            ButtonType.STICKER -> searchView.hint = "Search Stickers"
        }
        searchView.requestFocus()
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(searchView, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun setupSearchListView() {
        manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = SearchMediaAdapter(dataList, this)
        productsListView.layoutManager = manager
        productsListView.adapter = adapter
    }

    enum class ButtonType {
        YOUTUBE,
        GIF,
        STICKER
    }

}