package com.sikaplun.gb.kotlin.githubuseapi.di.module

import com.sikaplun.gb.kotlin.githubuseapi.data.api.ApiGitHub
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

val retrofitModule = module {
    single<ApiGitHub> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiGitHub::class.java)
    }
}