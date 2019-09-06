package com.blindmatch.chatan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blindmatch.chatex.Chatex
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // var isChatexVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        floatingActionButton.setOnClickListener {
            Chatex.toggleView(chatexView, it.x, it.y)
        }
    }
}
