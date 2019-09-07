package com.blindmatch.chatex.model

object Youtube{

    data class YoutubeUrlResult(val title:String,val thumbnail_url:String,val html:String)

    data class YoutubeSearchResult(val items:List<YoutubeSearchItem>)

    data class YoutubeTrendingResult(val items:List<YoutubeTrendingItem>)

    data class YoutubeTrendingItem(val id:String,val snippet:YoutubeSnippet?)

    data class YoutubeSearchItem(val id:YoutubeID,val snippet:YoutubeSnippet?)

    data class YoutubeID(val videoId:String)

    data class YoutubeSnippet(val publishedAt:String
                              ,val title:String
                              ,val description:String
                              ,val channelTitle:String,val thumbnails:YoutubeThumbnails)

    data class YoutubeThumbnails(val default:YoutubeThumbnail
                                 ,val medium:YoutubeThumbnail
                                 ,val high:YoutubeThumbnail)

    data class YoutubeThumbnail(val url:String
                                ,val width:Int
                                ,val height:Int)

}

data class IPData(val country:String,
                  val region:String,
                  val city:String)

