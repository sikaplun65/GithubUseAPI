package com.sikaplun.gb.kotlin.githubuseapi.data.api

import com.sikaplun.gb.kotlin.githubuseapi.data.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiGitHub {
    @GET("search/users")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}/repos")
    fun getRepo(
        @Path("username") username: String
    ): Call<ArrayList<UserRepository>>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username:String
    ): Call<DetailUserResponse>

}