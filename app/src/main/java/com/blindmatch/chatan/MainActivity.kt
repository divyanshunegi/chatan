package com.blindmatch.chatan

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blindmatch.chatex.Chatex
import com.blindmatch.chatex.model.MediaData
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
            giphKey = "9uR8VZoyzBI56xA9f9IQDE02j9ob1jF3",
            youtubeKey = "AIzaSyAz8hdZGnjGDxZZj2bcbA-a48hIsVmNfJ0"
        )
        Chatex.addListener(this)
        floatingActionButton.setOnClickListener {
            Chatex.toggleView(it.x, it.y)
        }
    }

    override fun onBackPressed() {
        if (Chatex.chatExBackPressHandler()) {
            super.onBackPressed()
        }
    }
}
