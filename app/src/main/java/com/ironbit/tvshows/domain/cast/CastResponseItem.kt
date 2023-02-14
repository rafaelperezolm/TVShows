package com.ironbit.tvshows.domain.cast


import com.google.gson.annotations.SerializedName

data class CastResponseItem(
    @SerializedName("character")
    val character: Character? = Character(),
    @SerializedName("person")
    val person: Person? = Person()
)

data class Character(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("image")
    val image: Image? = Image(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("url")
    val url: String? = ""
)

data class Image(
    @SerializedName("medium")
    val medium: String? = "",
    @SerializedName("original")
    val original: String? = ""
)

data class Person(
    val name: String? = "",
)