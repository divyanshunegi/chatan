package com.blindmatch.chatex.model

import androidx.annotation.Keep

/**
 * Created by divyanshunegi on 2019-09-07.
 * Project : Chatan
 */
@Keep
data class MediaData(
    var title: String? = "",
    var mediaLink: String? = "",
    var previewImage: String? = "",
    var videoId: String? = "",
    var videoLength: String = "",
    var type: Int = MediaType.UNSUPPORTED
)

class MediaType {
    companion object {
        const val GIF: Int = 0
        const val YOUTUBE: Int = 1
        const val UNSUPPORTED: Int = 2
        const val STICKER: Int = 3
    }
}