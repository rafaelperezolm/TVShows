package com.ironbit.tvshows.domain.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailItem(
    val id: Int = 0,
    val imageMedium: String = "",
    val name: String = "",
    val networkName: String = "",
    val networkRankingAverage: Double = 0.0,
    val officialSite: String = "",
    val summary: String = "",
    val genres: List<String> = listOf(),
    val airDate: String = "",
    val airTime: String = "",
    val scheduleTimes: String = "",
    val scheduleDays: List<String> = listOf()
) : Parcelable