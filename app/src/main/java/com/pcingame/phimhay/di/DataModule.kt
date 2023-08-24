package com.pcingame.phimhay.di

import com.google.gson.Gson
import com.pcingame.phimhay.BuildConfig
import com.pcingame.phimhay.data.local.AppDatabase
import com.pcingame.phimhay.data.remote.MovieApi
import com.pcingame.phimhay.data.remote.ServiceGenerator
import com.pcingame.phimhay.data.remote.middleware.ApiKeyInterceptor
import com.pcingame.phimhay.data.remote.middleware.TokenInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        AppDatabase.build(androidContext())
    }


    single {
        ServiceGenerator.buildRestApi(
            baseUrl = BuildConfig.BASE_URL,
            restApi = MovieApi::class.java,
            gson = Gson(),
            interceptors = listOf(
                TokenInterceptor(),
                ApiKeyInterceptor()
            )
        )
    }
}
