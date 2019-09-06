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

    fun toggleView(view: View?, touchX: Float = 0f, touchY: Float = 0f) {
        if (view == null) {
            throw RuntimeException("Toggle View must be passed with the ChatEx View you are trying to toggle")
        }
        if (isShareViewVisible) {
            isShareViewVisible = false
            hide(view, touchX, touchY)
        } else {
            isShareViewVisible = true
            show(view, touchX, touchY)
        }
    }

    private fun hide(view: View, touchX: Float = 0f, touchY: Float = 0f) {
        // get the center for the clipping circle
        val cx: Float = if (touchX == 0f) view.width / 2f else touchX
        val cy: Float = if (touchY == 0f) view.height / 2f else touchY

        // get the final radius for the clipping circle
        val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

        // create the animator for this view (the start radius is zero)
        val anim =
            ViewAnimationUtils.createCircularReveal(view, cx.toInt(), cy.toInt(), finalRadius, 0f)
        // make the view visible and start the animation
        anim.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                view.visibility = View.INVISIBLE
            }
        })
        anim.start()
    }

    private fun show(view: View, touchX: Float = 0f, touchY: Float = 0f) {
        // get the center for the clipping circle
        view.visibility = View.VISIBLE
        val cx: Float = if (touchX == 0f) view.width / 2f else touchX
        val cy: Float = if (touchY == 0f) view.height / 2f else touchY

        // get the final radius for the clipping circle
        val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

        // create the animator for this view (the start radius is zero)
        val anim =
            ViewAnimationUtils.createCircularReveal(view, cx.toInt(), cy.toInt(), 0f, finalRadius)
        // make the view visible and start the animation
        anim.start()
    }

    fun showYoutubeSearch() {

    }

    fun showGifSearch() {

    }

    fun showStickerSearch() {

    }

    fun chatExBackPressHandler(view: View?, touchX: Float = 0f, touchY: Float = 0f): Boolean {
        if (isShareViewVisible) {
            toggleView(view, touchX, touchY)
            return false
        }
        return true
    }

    interface ChatExListener {
        fun onYoutubeVideo()
        fun onGiphy()
        fun onSticker()
    }

}