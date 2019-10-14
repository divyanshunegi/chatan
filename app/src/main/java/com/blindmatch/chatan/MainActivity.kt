package com.blindmatch.chatan

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blindmatch.chatex.Chatex
import com.blindmatch.chatex.model.MediaData
import com.blindmatch.chatex.model.MediaType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Chatex.ChatexListener {

    override fun onMediaSelected(mediaData: MediaData) {
        Toast.makeText(this, mediaData.toString(), Toast.LENGTH_LONG).show()
    }

    // var isChatexVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Chatex.initialize(
            view = chatexView,
            giphKey = "giphy_api_key",
            youtubeKey = "youtube_key"
        )
        Chatex.addListener(this)
        floatingActionButtonYT.setOnClickListener {
            Toast.makeText(this, "Youtube", Toast.LENGTH_LONG).show()
            Chatex.toggleView(it.x, it.y, type = MediaType.YOUTUBE)
        }

        floatingActionButtonGif.setOnClickListener {
            Toast.makeText(this, "GIF", Toast.LENGTH_LONG).show()
            Chatex.toggleView(it.x, it.y, type = MediaType.GIF)
        }

        floatingActionButtonSticker.setOnClickListener {
            Toast.makeText(this, "Sticker", Toast.LENGTH_LONG).show()
            Chatex.toggleView(it.x, it.y, type = MediaType.STICKER)
        }
    }

    override fun onBackPressed() {
        if (Chatex.chatExBackPressHandler()) {
            super.onBackPressed()
        }
    }
}
