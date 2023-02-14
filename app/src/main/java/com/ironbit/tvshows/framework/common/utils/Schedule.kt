package com.ironbit.tvshows.framework.common.utils

import com.ironbit.tvshows.domain.detail.DetailItem
import com.ironbit.tvshows.domain.schedule.ScheduleResponseItem

// Converts the obtained schedule data to a detail data class
fun List<ScheduleResponseItem>.toDetailItemList(): List<DetailItem> {
    val detailInfoList: MutableList<DetailItem> = mutableListOf()
    this.forEach {
        detailInfoList.add(it.toDetailItem())
    }
    return detailInfoList
}

fun ScheduleResponseItem.toDetailItem(): DetailItem {
    return DetailItem(
        id = this.show?.id ?: 0,
        imageMedium = this.show?.image?.medium ?: "",
        name = this.show?.name ?: "",
        networkName = this.show?.network?.name ?: "",
        networkRankingAverage = this.show?.rating?.average ?: 0.0,
        officialSite = this.show?.officialSite ?: "",
        summary = this.summary ?: "",
        genres = this.show?.genres ?: listOf(),
        airTime = this.airtime.toString(),
        airDate = this.airdate.toString(),
        scheduleTimes = this.show?.schedule?.time ?: "",
        scheduleDays = this.show?.schedule?.days ?: listOf()
    )
}