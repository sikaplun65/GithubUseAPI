package com.sikaplun.gb.kotlin.githubuseapi.data.model

import com.google.gson.annotations.SerializedName

data class DetailUserRepository(
    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("language")
    val language: String
)
