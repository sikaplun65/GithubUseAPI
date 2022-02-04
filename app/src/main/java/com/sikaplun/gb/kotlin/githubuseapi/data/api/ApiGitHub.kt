package com.sikaplun.gb.kotlin.githubuseapi.data.api

import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserResponse
import com.sikaplun.gb.kotlin.githubuseapi.data.model.UserRepository
import com.sikaplun.gb.kotlin.githubuseapi.data.model.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiGitHub {
    @GET("search/users")
    fun getSearchUsers(
        @Query("q") query: String
    ): Single<UserResponse>

    @GET("users/{username}/repos")
    fun getRepo(
        @Path("username") username: String
    ): Single<ArrayList<UserRepository>>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username: String
    ): Single<DetailUserResponse>
}