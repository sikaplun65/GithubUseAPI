package com.sikaplun.gb.kotlin.githubuseapi.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserRepository
import com.sikaplun.gb.kotlin.githubuseapi.data.model.DetailUserResponse
import com.sikaplun.gb.kotlin.githubuseapi.data.repositories.DetailUserRepositoryRequest

class DetailUserActivityModelFactory: ViewModelProvider.Factory {

    private val user = MutableLiveData<DetailUserResponse>()
    private val repos = MutableLiveData<ArrayList<DetailUserRepository>>()
    private val userDetail by lazy { DetailUserRepositoryRequest() }

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        DetailUserActivityModel(user, repos, userDetail) as T
}