package com.ironbit.tvshows.data

import com.ironbit.tvshows.domain.cast.CastResponseItem
import com.ironbit.tvshows.domain.schedule.ScheduleResponseItem
import com.ironbit.tvshows.domain.search.SearchResponseItem
import com.ironbit.tvshows.domain.show.ShowResponse

class RepositoryImpl(
    private val api: ApiService
) : Repository {

    override suspend fun getSchedule(
        country: String,
        date: String
    ): List<ScheduleResponseItem> {
        return api.getSchedule(
            country = country,
            date = date
        )
    }

    override suspend fun getSearch(
        query: String
    ): List<SearchResponseItem> {
        return api.getSearch(
            query = query
        )
    }

    override suspend fun getShow(
        id: Int
    ): ShowResponse {
        return api.getShow(
            id = id
        )
    }

    override suspend fun getCast(
        id: Int
    ): List<CastResponseItem> {
        return api.getCast(
            id = id
        )
    }

}