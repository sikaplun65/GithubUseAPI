package com.sikaplun.gb.kotlin.githubuseapi.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sikaplun.gb.kotlin.githubuseapi.data.model.User
import com.sikaplun.gb.kotlin.githubuseapi.data.repositories.GitRepositoryRequest

class MainViewModelFactory : ViewModelProvider.Factory {
    private val repos by lazy { GitRepositoryRequest() }
    private val listUsers by lazy { MutableLiveData<ArrayList<User>>() }

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainViewModel(repos, listUsers) as T

}