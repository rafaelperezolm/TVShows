package com.ironbit.tvshows.data

import com.ironbit.tvshows.domain.cast.CastResponseItem
import com.ironbit.tvshows.domain.schedule.ScheduleResponseItem
import com.ironbit.tvshows.domain.search.SearchResponseItem
import com.ironbit.tvshows.domain.show.ShowResponse

interface Repository {

    suspend fun getSchedule(
        country: String,
        date: String
    ): List<ScheduleResponseItem>

    suspend fun getSearch(
        query: String
    ): List<SearchResponseItem>

    suspend fun getShow(
        id: Int
    ): ShowResponse

    suspend fun getCast(
        id: Int
    ): List<CastResponseItem>

}