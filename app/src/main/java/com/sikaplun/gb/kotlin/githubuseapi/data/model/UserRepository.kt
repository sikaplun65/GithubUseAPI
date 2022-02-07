package com.sikaplun.gb.kotlin.githubuseapi.data.model

import com.google.gson.annotations.SerializedName

data class UserRepository(
    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("language")
    val language: String
)
