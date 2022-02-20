package com.sikaplun.gb.kotlin.githubuseapi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sikaplun.gb.kotlin.githubuseapi.data.repositories.GitRepositoryRequest

class MainViewModelFactory(
    private val repos: GitRepositoryRequest
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repos) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }
}