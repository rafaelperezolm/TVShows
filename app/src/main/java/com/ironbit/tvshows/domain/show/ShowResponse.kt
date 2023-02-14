package com.ironbit.tvshows.domain.show


import com.google.gson.annotations.SerializedName

data class ShowResponse(
    @SerializedName("averageRuntime")
    val averageRuntime: Int? = 0,
    @SerializedName("dvdCountry")
    val dvdCountry: Any? = Any(),
    @SerializedName("ended")
    val ended: Any? = Any(),
    @SerializedName("externals")
    val externals: Externals? = Externals(),
    @SerializedName("genres")
    val genres: List<String?>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("image")
    val image: Image? = Image(),
    @SerializedName("language")
    val language: String? = "",
    @SerializedName("_links")
    val links: Links? = Links(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("network")
    val network: Network? = Network(),
    @SerializedName("officialSite")
    val officialSite: String? = "",
    @SerializedName("premiered")
    val premiered: String? = "",
    @SerializedName("rating")
    val rating: Rating? = Rating(),
    @SerializedName("runtime")
    val runtime: Int? = 0,
    @SerializedName("schedule")
    val schedule: Schedule? = Schedule(),
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("summary")
    val summary: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("updated")
    val updated: Int? = 0,
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("webChannel")
    val webChannel: Any? = Any(),
    @SerializedName("weight")
    val weight: Int? = 0
)

data class Externals(
    @SerializedName("imdb")
    val imdb: String? = "",
    @SerializedName("thetvdb")
    val thetvdb: Int? = 0,
    @SerializedName("tvrage")
    val tvrage: Int? = 0
)

data class Image(
    @SerializedName("medium")
    val medium: String? = "",
    @SerializedName("original")
    val original: String? = ""
)

data class Links(
    @SerializedName("nextepisode")
    val nextepisode: Nextepisode? = Nextepisode(),
    @SerializedName("previousepisode")
    val previousepisode: Previousepisode? = Previousepisode(),
    @SerializedName("self")
    val self: Self? = Self()
)

data class Nextepisode(
    @SerializedName("href")
    val href: String? = ""
)

data class Previousepisode(
    @SerializedName("href")
    val href: String? = ""
)

data class Self(
    @SerializedName("href")
    val href: String? = ""
)

data class Network(
    @SerializedName("country")
    val country: Country? = Country(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("officialSite")
    val officialSite: String? = ""
)

data class Country(
    @SerializedName("code")
    val code: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("timezone")
    val timezone: String? = ""
)

data class Rating(
    @SerializedName("average")
    val average: Double? = 0.0
)

data class Schedule(
    @SerializedName("days")
    val days: List<String?>? = listOf(),
    @SerializedName("time")
    val time: String? = ""
)