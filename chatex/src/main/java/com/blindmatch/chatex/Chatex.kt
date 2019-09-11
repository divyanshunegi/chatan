package com.blindmatch.chatex

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils
import com.blindmatch.chatex.model.MediaData
import com.blindmatch.chatex.model.MediaType
import kotlin.math.hypot

/**
 * Created by divyanshunegi on 2019-09-06.
 * Project : Chatan
 */
object Chatex {

    private var isShareViewVisible = false
    private var localtouchX = 0f
    private var localtouchY = 0f
    private lateinit var chatexView: ChatExView
    private var listener: ChatexListener? = null
    private var youtubedefaultlList: MutableList<MediaData> = mutableListOf()
    private var GIFdefaultlList: MutableList<MediaData> = mutableListOf()
    private var stickerdefaultlList: MutableList<MediaData> = mutableListOf()
    var giphyKey = ""
    var youtubeKey = ""


    /**
     * Pass the ChatexView added in your layout
     * @param(view: ChatExView added in your layout,
     * X cordinate for the touch icon which will trigger this view,
     * Y cordinate for the touch icon which will trigger this view)
     */
    fun initialize(view: ChatExView? = null, giphKey: String = "", youtubeKey: String = "") {
        if (view == null) {
            throw RuntimeException("Toggle View must be passed with the ChatEx View you are trying to toggle")
        }
        this.giphyKey = giphKey
        this.youtubeKey = youtubeKey
        chatexView = view
    }

    fun toggleView(touchX: Float = 0f, touchY: Float = 0f) {
        if (touchX != 0f || touchY != 0f) {
            localtouchX = touchX
            localtouchY = touchY
        }
        if (isShareViewVisible) {
            isShareViewVisible = false
            hide()
        } else {
            isShareViewVisible = true
            show()
        }
    }

    private fun hide() {
        // get the center for the clipping circle
        val cx: Float = if (localtouchX == 0f) chatexView.width / 2f else localtouchX
        val cy: Float = if (localtouchY == 0f) chatexView.height / 2f else localtouchY

        // get the final radius for the clipping circle
        val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

        // create the animator for this view (the start radius is zero)
        val anim =
            ViewAnimationUtils.createCircularReveal(
                chatexView,
                cx.toInt(),
                cy.toInt(),
                finalRadius,
                0f
            )
        // make the view visible and start the animation
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                chatexView.visibility = View.INVISIBLE
            }
        })
        anim.start()
    }

    private fun show() {
        // get the center for the clipping circle
        chatexView.visibility = View.VISIBLE
        val cx: Float = if (localtouchX == 0f) chatexView.width / 2f else localtouchX
        val cy: Float = if (localtouchY == 0f) chatexView.height / 2f else localtouchY

        // get the final radius for the clipping circle
        val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

        // create the animator for this view (the start radius is zero)
        val anim =
            ViewAnimationUtils.createCircularReveal(
                chatexView,
                cx.toInt(),
                cy.toInt(),
                0f,
                finalRadius
            )
        // make the view visible and start the animation
        anim.start()
    }

    fun chatExBackPressHandler(): Boolean {
        if (isShareViewVisible) {
            toggleView()
            return false
        }
        return true
    }

    fun addListener(chatexListener: ChatexListener) {
        this.listener = chatexListener
    }


    fun mediaSelected(mediaData: MediaData) {
        this.listener?.onMediaSelected(mediaData)
    }

    fun getYoutubeCachedList(): List<MediaData> {
        if (this.youtubedefaultlList.isNotEmpty()) {
            return this.youtubedefaultlList
        }
        return mutableListOf(
            MediaData(
                title = "WATCH and TRY TO STOP LAUGHING",
                mediaLink = "https://img.youtube.com/vi/DODLEX4zzLQ/mqdefault.jpg",
                type = MediaType.YOUTUBE,
                videoId = "DODLEX4zzLQ"
            ),
            MediaData(
                title = "Top 100 Viral Videos of the Year",
                mediaLink = "https://img.youtube.com/vi/uQl-r3pqnnQ/mqdefault.jpg",
                type = MediaType.YOUTUBE,
                videoId = "uQl-r3pqnnQ"
            ),
            MediaData(
                title = "FUNNY VIDEOS FROM THE INTERNET",
                mediaLink = "https://img.youtube.com/vi/g3Dob0wEcSc/mqdefault.jpg",
                type = MediaType.YOUTUBE,
                videoId = "g3Dob0wEcSc"
            ),
            MediaData(
                title = "TRY NOT TO LAUGH",
                mediaLink = "https://img.youtube.com/vi/nKQpRkp5jYE/mqdefault.jpg",
                type = MediaType.YOUTUBE,
                videoId = "nKQpRkp5jYE"
            ),
            MediaData(
                title = "50 Best Viral Videos Of The Year",
                mediaLink = "https://img.youtube.com/vi/l4a7MBfz-sw/mqdefault.jpg",
                type = MediaType.YOUTUBE,
                videoId = "l4a7MBfz-sw"
            )
        )
    }

    fun getGIFCachedList(): List<MediaData> {
        if (this.GIFdefaultlList.isNotEmpty()) {
            return this.GIFdefaultlList
        }
        return mutableListOf(
            MediaData(
                mediaLink = "https://media.giphy.com/media/Cmr1OMJ2FN0B2/200w_d.gif",
                type = MediaType.GIF
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/dzaUX7CAG0Ihi/200w_d.gif",
                type = MediaType.GIF
            ),
            MediaData(
                mediaLink = "https://giphygifs.s3.amazonaws.com/media/ASd0Ukj0y3qMM/200w_d.gif",
                type = MediaType.GIF
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/xT9IgG50Fb7Mi0prBC/200w_d.gif",
                type = MediaType.GIF
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/5xtDarEbygs3Pu7p3jO/giphy-downsized.gif",
                type = MediaType.GIF
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/LFay0DyV6urKw/giphy-downsized.gif",
                type = MediaType.GIF
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/vFKqnCdLPNOKc/giphy.gif",
                type = MediaType.GIF
            )
        )
    }

    fun getStickerCachedList(): List<MediaData> {
        if (this.stickerdefaultlList.isNotEmpty()) {
            return this.stickerdefaultlList
        }
        return mutableListOf(
            MediaData(
                mediaLink = "https://media.giphy.com/media/h5WxadLQfXFl3Colmx/200w_d.gif",
                type = MediaType.STICKER
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/mAPo2ZuRc09ag0Wvaj/giphy-downsized.gif",
                type = MediaType.STICKER
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/ZeKu0hv1fggmQd2Eoq/giphy.gif",
                type = MediaType.STICKER
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/TgCQ7zRed8bVEA8jBW/giphy.gif",
                type = MediaType.STICKER
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/cNwgtuyhJ59y6Tl4q8/giphy-downsized.gif",
                type = MediaType.STICKER
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/mA15xf85a0F89E716f/giphy-downsized.gif",
                type = MediaType.STICKER
            ),

            MediaData(
                mediaLink = "https://media.giphy.com/media/3oKIPApSAGEH65t9Ha/200w_d.gif",
                type = MediaType.STICKER
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/12PRuWQ9KEieT6/giphy-downsized.gif",
                type = MediaType.STICKER
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/3oKIPdF5sBePN9DYFG/giphy.gif",
                type = MediaType.STICKER
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/3oKIPh0y7hHGTFoQhy/200w_d.gif",
                type = MediaType.STICKER
            ),
            MediaData(
                mediaLink = "https://media.giphy.com/media/3oKIPCG7JBINSdfxQc/giphy.gif",
                type = MediaType.STICKER
            )
        )
    }

    fun addYoutubeDefaultData(youtubeData: List<MediaData>) {
        this.youtubedefaultlList.clear()
        this.youtubedefaultlList.addAll(youtubeData)
    }

    fun addGIFDefaultData(youtubeData: List<MediaData>) {
        this.GIFdefaultlList.clear()
        this.GIFdefaultlList.addAll(youtubeData)
    }

    fun addStickerDefaultData(youtubeData: List<MediaData>) {
        this.stickerdefaultlList.clear()
        this.stickerdefaultlList.addAll(youtubeData)
    }


    interface ChatexListener {
        fun onMediaSelected(mediaData: MediaData)
    }
}