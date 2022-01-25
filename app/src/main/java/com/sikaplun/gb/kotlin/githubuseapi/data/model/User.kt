package com.sikaplun.gb.kotlin.githubuseapi.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val login: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("avatar_url")
    val avatarUrl: String
)
