package com.blindmatch.chatex

import android.content.Context
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.Keep
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blindmatch.chatex.adapter.MediaAdapterListener
import com.blindmatch.chatex.adapter.SearchMediaAdapter
import com.blindmatch.chatex.api.ChatexService
import com.blindmatch.chatex.model.MediaData
import com.blindmatch.chatex.model.MediaType
import com.blindmatch.chatex.utils.ListSpacingDecoration
import kotlinx.android.synthetic.main.new_options_view.view.*
import kotlinx.android.synthetic.main.options_view.view.buttonLayout
import kotlinx.android.synthetic.main.options_view.view.gif_select
import kotlinx.android.synthetic.main.options_view.view.ic_cancel_button
import kotlinx.android.synthetic.main.options_view.view.productsListView
import kotlinx.android.synthetic.main.options_view.view.progressBar
import kotlinx.android.synthetic.main.options_view.view.searchView
import kotlinx.android.synthetic.main.options_view.view.search_icon
import kotlinx.android.synthetic.main.options_view.view.sticker_select
import kotlinx.android.synthetic.main.options_view.view.youtube_select
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
                            type = MediaType.STICKER
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
        dataList.clear()
        adapter.notifyDataSetChanged()
        // ic_cancel_button.performClick()
        Chatex.toggleView()
        Chatex.mediaSelected(mediaData)
    }

    private lateinit var adapter: SearchMediaAdapter
    private lateinit var manager: StaggeredGridLayoutManager
    private val dataList: MutableList<MediaData> = mutableListOf()

    init {
        this.visibility = View.GONE
        val inflater = LayoutInflater.from(getContext())
        inflater.inflate(R.layout.new_options_view, this)
        close_button.setOnClickListener {
            Chatex.toggleView()
        }
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
            dataList.addAll(Chatex.getYoutubeCachedList())
            adapter.notifyDataSetChanged()
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
            dataList.addAll(Chatex.getGIFCachedList())
            adapter.notifyDataSetChanged()
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
            dataList.addAll(Chatex.getStickerCachedList())
            adapter.notifyDataSetChanged()
        }

        setupSearchListView()
        addTextWatcherOnSearch()
    }

    private fun hidekeyboard() {
//        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(windowToken, 0)
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

    private fun setSearchHint(hintText: String = "") {
        searchView.hint = hintText
//        searchView.requestFocus()
//        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.showSoftInput(searchView, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun setupSearchListView() {
        // manager = SpeedyLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        manager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        adapter = SearchMediaAdapter(dataList, this)
        productsListView.layoutManager = manager
        productsListView.setHasFixedSize(true)
        productsListView.addItemDecoration(ListSpacingDecoration(context, R.dimen.list_spacing))
        productsListView.adapter = adapter
    }

    fun enableType(type: Int) {
        when (type) {
            MediaType.GIF -> {
                selected = ButtonType.GIF
                title.text = "GIF"
                setSearchHint("Search GIF POWERED BY GIPHY...")
                dataList.clear()
                dataList.addAll(Chatex.getGIFCachedList())
                adapter.notifyDataSetChanged()
            }
            MediaType.YOUTUBE -> {
                selected = ButtonType.YOUTUBE
                title.text = "YouTube"
                setSearchHint("Search Videos...")
                dataList.clear()
                dataList.addAll(Chatex.getYoutubeCachedList())
                adapter.notifyDataSetChanged()
            }
            MediaType.STICKER -> {
                selected = ButtonType.STICKER
                title.text = "Stickers"
                setSearchHint("Search Sticker...")
                dataList.clear()
                dataList.addAll(Chatex.getStickerCachedList())
                adapter.notifyDataSetChanged()
            }
        }
    }

    @Keep
    enum class ButtonType {
        YOUTUBE,
        GIF,
        STICKER
    }

}