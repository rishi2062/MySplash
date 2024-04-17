package com.example.mysplash.data.remote

data class SplashPhotosResponse(
    val data: List<SplashPhotosItem> = listOf(),
)

data class SplashPhotosItem(
    val id: String = "",
    val color: String = "",
    val alt_description: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val liked_by_user: Boolean = false,
    val likes: Int = 0,
    val links: Links = Links(),
    val urls: Urls = Urls(),
    val user: User = User(),
)

data class Links(
    val self: String = "",
    val html: String = "",
    val download: String = "",
    val download_location: String = "",
)

data class Urls(
    val raw: String = "",
    val full: String = "",
    val regular: String = "",
    val small: String = "",
    val thumb: String = "",
)