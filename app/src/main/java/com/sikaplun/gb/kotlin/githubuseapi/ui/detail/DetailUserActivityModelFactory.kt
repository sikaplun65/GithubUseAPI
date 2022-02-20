package com.sikaplun.gb.kotlin.githubuseapi.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sikaplun.gb.kotlin.githubuseapi.data.repositories.DetailUserRepositoryRequest

class DetailUserActivityModelFactory(private val userDetail: DetailUserRepositoryRequest) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailUserActivityModel::class.java)){
            return DetailUserActivityModel(userDetail) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}