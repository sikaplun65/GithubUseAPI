package com.sikaplun.gb.kotlin.githubuseapi.data.model

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(
    @SerializedName("login")
    val login: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,
)
