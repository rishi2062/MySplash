package com.example.mysplash.data.remote

data class User(
    val id: String = "",
    val username: String = "",
    val name: String = "",
    val profile_image: ProfileImage = ProfileImage()
)

data class ProfileImage(
    val small: String = "",
    val medium: String = "",
    val large: String = ""
)