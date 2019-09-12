package com.blindmatch.chatex.model

import androidx.annotation.Keep

object Youtube{

    @Keep
    data class YoutubeUrlResult(val title:String,val thumbnail_url:String,val html:String)

    @Keep
    data class YoutubeSearchResult(val items:List<YoutubeSearchItem>)

    @Keep
    data class YoutubeTrendingResult(val items:List<YoutubeTrendingItem>)

    @Keep
    data class YoutubeTrendingItem(val id:String,val snippet:YoutubeSnippet?)

    @Keep
    data class YoutubeSearchItem(val id:YoutubeID,val snippet:YoutubeSnippet?)

    @Keep
    data class YoutubeID(val videoId:String)

    @Keep
    data class YoutubeSnippet(val publishedAt:String
                              ,val title:String
                              ,val description:String
                              ,val channelTitle:String,val thumbnails:YoutubeThumbnails)

    @Keep
    data class YoutubeThumbnails(val default:YoutubeThumbnail
                                 ,val medium:YoutubeThumbnail
                                 ,val high:YoutubeThumbnail)

    @Keep
    data class YoutubeThumbnail(val url:String
                                ,val width:Int
                                ,val height:Int)

}

@Keep
data class IPData(val country:String,
                  val region:String,
                  val city:String)

