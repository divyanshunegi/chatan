package com.blindmatch.chatex

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils
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
    private var giphyKey = ""

    /**
     * Pass the ChatexView added in your layout
     * @param(view: ChatExView added in your layout,
     * X cordinate for the touch icon which will trigger this view,
     * Y cordinate for the touch icon which will trigger this view)
     */
    fun initView(view: ChatExView?) {
        if (view == null) {
            throw RuntimeException("Toggle View must be passed with the ChatEx View you are trying to toggle")
        }
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

    fun showYoutubeSearch() {

    }

    fun showGifSearch() {

    }

    fun showStickerSearch() {

    }

    fun chatExBackPressHandler(): Boolean {
        if (isShareViewVisible) {
            toggleView()
            return false
        }
        return true
    }

    fun setGiphyKey(key: String) {
        giphyKey = key
    }

    interface ChatExListener {
        fun onYoutubeVideo()
        fun onGiphy()
        fun onSticker()
    }

}