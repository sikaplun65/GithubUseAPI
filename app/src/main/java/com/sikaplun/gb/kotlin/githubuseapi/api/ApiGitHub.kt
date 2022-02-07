package com.sikaplun.gb.kotlin.githubuseapi.api

import com.sikaplun.gb.kotlin.githubuseapi.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiGitHub {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>
}