package com.ironbit.tvshows.framework

import com.ironbit.tvshows.data.ApiService
import com.ironbit.tvshows.data.Repository
import com.ironbit.tvshows.data.RepositoryImpl
import com.ironbit.tvshows.framework.common.BASE_URL
import com.ironbit.tvshows.presentation.detail.DetailViewModel
import com.ironbit.tvshows.presentation.list.ListViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModule = module {

    single {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    single<Repository> {
        RepositoryImpl(get())
    }

    viewModel {
        ListViewModel(get())
    }
    viewModel {
        DetailViewModel(get())
    }

}