package com.blindmatch.chatex.api

import com.blindmatch.chatex.Chatex
import com.blindmatch.chatex.model.GiphyModel
import com.blindmatch.chatex.model.Youtube
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ChatexService {

    companion object {
        fun create(url: String? = null): ChatexService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    CoroutineCallAdapterFactory()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(url)
                .build()

//            if (client != null) {
//                retrofit = Retrofit.Builder()
//                    .addCallAdapterFactory(
//                        CoroutineCallAdapterFactory()
//                    )
//                    .addConverterFactory(
//                        GsonConverterFactory.create()
//                    )
//                    .client(client)
//                    .baseUrl(newUrl)
//                    .build()
//            }

            return retrofit.create(ChatexService::class.java)
        }

        fun getYoutubeClient() : ChatexService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    CoroutineCallAdapterFactory()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl("https://www.googleapis.com/youtube/")
                .build()
            return retrofit.create(ChatexService::class.java)
        }

        fun getGiphyClient(type:String = "gifs"): ChatexService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    CoroutineCallAdapterFactory()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl("https://api.giphy.com/v1/$type/")
                .build()
            return retrofit.create(ChatexService::class.java)
        }
    }

    @GET("search")
    fun searchGiphy(
        @Query("api_key") part: String = Chatex.giphyKey,
        @Query("q") query: String = "hi",
        @Query("limit") limit: Int = 10,
        @Query("rating") rating: String = "PG-13",
        @Query("lang") lang: String = "en"
    ): Deferred<GiphyModel>

    @GET("v3/search")
    fun getVideosByKeyword(
        @Query("part") part: String = "snippet",
        @Query("q") query: String,
        @Query("type") type: String = "video",
        @Query("key") key: String = Chatex.youtubeKey,
        @Query("maxResults") maxResults: Int = 25
    ):
            Deferred<Youtube.YoutubeSearchResult>

}