package com.blindmatch.chatex.model


import com.google.gson.annotations.SerializedName

data class GiphyModel(
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("meta")
    val meta: Meta = Meta(),
    @SerializedName("pagination")
    val pagination: Pagination = Pagination()
) {
    data class Pagination(
        @SerializedName("count")
        val count: Int = 0,
        @SerializedName("offset")
        val offset: Int = 0,
        @SerializedName("total_count")
        val totalCount: Int = 0
    )

    data class Data(
        @SerializedName("analytics")
        val analytics: Analytics = Analytics(),
        @SerializedName("bitly_gif_url")
        val bitlyGifUrl: String = "",
        @SerializedName("bitly_url")
        val bitlyUrl: String = "",
        @SerializedName("content_url")
        val contentUrl: String = "",
        @SerializedName("embed_url")
        val embedUrl: String = "",
        @SerializedName("id")
        val id: String = "",
        @SerializedName("images")
        val images: Images = Images(),
        @SerializedName("import_datetime")
        val importDatetime: String = "",
        @SerializedName("is_sticker")
        val isSticker: Int = 0,
        @SerializedName("rating")
        val rating: String = "",
        @SerializedName("slug")
        val slug: String = "",
        @SerializedName("source")
        val source: String = "",
        @SerializedName("source_post_url")
        val sourcePostUrl: String = "",
        @SerializedName("source_tld")
        val sourceTld: String = "",
        @SerializedName("title")
        val title: String = "",
        @SerializedName("trending_datetime")
        val trendingDatetime: String = "",
        @SerializedName("type")
        val type: String = "",
        @SerializedName("url")
        val url: String = "",
        @SerializedName("user")
        val user: User = User(),
        @SerializedName("username")
        val username: String = ""
    ) {
        data class Images(
            @SerializedName("downsized")
            val downsized: Downsized = Downsized(),
            @SerializedName("downsized_large")
            val downsizedLarge: DownsizedLarge = DownsizedLarge(),
            @SerializedName("downsized_medium")
            val downsizedMedium: DownsizedMedium = DownsizedMedium(),
            @SerializedName("downsized_small")
            val downsizedSmall: DownsizedSmall = DownsizedSmall(),
            @SerializedName("downsized_still")
            val downsizedStill: DownsizedStill = DownsizedStill(),
            @SerializedName("fixed_height")
            val fixedHeight: FixedHeight = FixedHeight(),
            @SerializedName("fixed_height_downsampled")
            val fixedHeightDownsampled: FixedHeightDownsampled = FixedHeightDownsampled(),
            @SerializedName("fixed_height_small")
            val fixedHeightSmall: FixedHeightSmall = FixedHeightSmall(),
            @SerializedName("fixed_height_small_still")
            val fixedHeightSmallStill: FixedHeightSmallStill = FixedHeightSmallStill(),
            @SerializedName("fixed_height_still")
            val fixedHeightStill: FixedHeightStill = FixedHeightStill(),
            @SerializedName("fixed_width")
            val fixedWidth: FixedWidth = FixedWidth(),
            @SerializedName("fixed_width_downsampled")
            val fixedWidthDownsampled: FixedWidthDownsampled = FixedWidthDownsampled(),
            @SerializedName("fixed_width_small")
            val fixedWidthSmall: FixedWidthSmall = FixedWidthSmall(),
            @SerializedName("fixed_width_small_still")
            val fixedWidthSmallStill: FixedWidthSmallStill = FixedWidthSmallStill(),
            @SerializedName("fixed_width_still")
            val fixedWidthStill: FixedWidthStill = FixedWidthStill(),
            @SerializedName("looping")
            val looping: Looping = Looping(),
            @SerializedName("original")
            val original: Original = Original(),
            @SerializedName("original_mp4")
            val originalMp4: OriginalMp4 = OriginalMp4(),
            @SerializedName("original_still")
            val originalStill: OriginalStill = OriginalStill(),
            @SerializedName("preview")
            val preview: Preview = Preview(),
            @SerializedName("preview_gif")
            val previewGif: PreviewGif = PreviewGif(),
            @SerializedName("preview_webp")
            val previewWebp: PreviewWebp = PreviewWebp(),
            @SerializedName("480w_still")
            val wStill: WStill = WStill()
        ) {
            data class FixedHeight(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("mp4")
                val mp4: String = "",
                @SerializedName("mp4_size")
                val mp4Size: String = "",
                @SerializedName("size")
                val size: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("webp")
                val webp: String = "",
                @SerializedName("webp_size")
                val webpSize: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class OriginalStill(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class OriginalMp4(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("mp4")
                val mp4: String = "",
                @SerializedName("mp4_size")
                val mp4Size: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class FixedWidthDownsampled(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("size")
                val size: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("webp")
                val webp: String = "",
                @SerializedName("webp_size")
                val webpSize: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class FixedHeightStill(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class FixedWidthSmall(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("mp4")
                val mp4: String = "",
                @SerializedName("mp4_size")
                val mp4Size: String = "",
                @SerializedName("size")
                val size: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("webp")
                val webp: String = "",
                @SerializedName("webp_size")
                val webpSize: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class FixedHeightSmallStill(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class Looping(
                @SerializedName("mp4")
                val mp4: String = "",
                @SerializedName("mp4_size")
                val mp4Size: String = ""
            )

            data class DownsizedMedium(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("size")
                val size: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class Original(
                @SerializedName("frames")
                val frames: String = "",
                @SerializedName("height")
                val height: String = "",
                @SerializedName("mp4")
                val mp4: String = "",
                @SerializedName("mp4_size")
                val mp4Size: String = "",
                @SerializedName("size")
                val size: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("webp")
                val webp: String = "",
                @SerializedName("webp_size")
                val webpSize: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class Preview(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("mp4")
                val mp4: String = "",
                @SerializedName("mp4_size")
                val mp4Size: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class WStill(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class PreviewWebp(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("size")
                val size: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class Downsized(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("size")
                val size: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class FixedHeightSmall(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("mp4")
                val mp4: String = "",
                @SerializedName("mp4_size")
                val mp4Size: String = "",
                @SerializedName("size")
                val size: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("webp")
                val webp: String = "",
                @SerializedName("webp_size")
                val webpSize: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class DownsizedLarge(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("size")
                val size: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class DownsizedSmall(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("mp4")
                val mp4: String = "",
                @SerializedName("mp4_size")
                val mp4Size: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class FixedWidthSmallStill(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class FixedWidth(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("mp4")
                val mp4: String = "",
                @SerializedName("mp4_size")
                val mp4Size: String = "",
                @SerializedName("size")
                val size: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("webp")
                val webp: String = "",
                @SerializedName("webp_size")
                val webpSize: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class FixedWidthStill(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class PreviewGif(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("size")
                val size: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class DownsizedStill(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("size")
                val size: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: String = ""
            )

            data class FixedHeightDownsampled(
                @SerializedName("height")
                val height: String = "",
                @SerializedName("size")
                val size: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("webp")
                val webp: String = "",
                @SerializedName("webp_size")
                val webpSize: String = "",
                @SerializedName("width")
                val width: String = ""
            )
        }

        data class Analytics(
            @SerializedName("onclick")
            val onclick: Onclick = Onclick(),
            @SerializedName("onload")
            val onload: Onload = Onload(),
            @SerializedName("onsent")
            val onsent: Onsent = Onsent()
        ) {
            data class Onsent(
                @SerializedName("url")
                val url: String = ""
            )

            data class Onclick(
                @SerializedName("url")
                val url: String = ""
            )

            data class Onload(
                @SerializedName("url")
                val url: String = ""
            )
        }

        data class User(
            @SerializedName("avatar_url")
            val avatarUrl: String = "",
            @SerializedName("banner_image")
            val bannerImage: String = "",
            @SerializedName("banner_url")
            val bannerUrl: String = "",
            @SerializedName("display_name")
            val displayName: String = "",
            @SerializedName("is_verified")
            val isVerified: Boolean = false,
            @SerializedName("profile_url")
            val profileUrl: String = "",
            @SerializedName("username")
            val username: String = ""
        )
    }

    data class Meta(
        @SerializedName("msg")
        val msg: String = "",
        @SerializedName("response_id")
        val responseId: String = "",
        @SerializedName("status")
        val status: Int = 0
    )
}