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
        Chatex.initView(chatexView)
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
