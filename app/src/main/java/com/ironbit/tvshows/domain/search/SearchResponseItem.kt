package com.ironbit.tvshows.domain.search

import com.google.gson.annotations.SerializedName

data class SearchResponseItem(
    @SerializedName("show")
    val show: Show? = Show()
)

data class Show(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("network")
    val network: Network? = Network(),
    @SerializedName("image")
    val image: Image? = Image(),
    @SerializedName("summary")
    val summary: String? = "",
    @SerializedName("rating")
    val rating: Rating? = Rating(),
    @SerializedName("officialSite")
    val officialSite: String? = "",
    @SerializedName("schedule")
    val schedule: Schedule? = Schedule(),
    @SerializedName("genres")
    val genres: List<String>? = listOf(),
)

data class Schedule(
    @SerializedName("time")
    val time: String? = "",
    @SerializedName("days")
    val days: List<String>? = listOf()
)

data class Network(
    @SerializedName("name")
    val name: String? = ""
)

data class Image(
    @SerializedName("medium")
    val medium: String? = ""
)

data class Rating(
    @SerializedName("average")
    val average: Double? = 0.0
)