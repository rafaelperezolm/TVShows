package com.ironbit.tvshows.data

import com.ironbit.tvshows.domain.cast.CastResponseItem
import com.ironbit.tvshows.domain.schedule.ScheduleResponseItem
import com.ironbit.tvshows.domain.search.SearchResponseItem
import com.ironbit.tvshows.domain.show.ShowResponse
import com.ironbit.tvshows.framework.common.CAST_PATH
import com.ironbit.tvshows.framework.common.SCHEDULE_PATH
import com.ironbit.tvshows.framework.common.SEARCH_PATH
import com.ironbit.tvshows.framework.common.SHOW_PATH
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(SCHEDULE_PATH)
    suspend fun getSchedule(
        @Query("country") country: String,
        @Query("date") date: String
    ): List<ScheduleResponseItem>

    @GET(SEARCH_PATH)
    suspend fun getSearch(
        @Query("q") query: String
    ): List<SearchResponseItem>

    @GET(SHOW_PATH)
    suspend fun getShow(
        @Path("id") id: Int
    ): ShowResponse

    @GET(CAST_PATH)
    suspend fun getCast(
        @Path("id") id: Int
    ): List<CastResponseItem>

}