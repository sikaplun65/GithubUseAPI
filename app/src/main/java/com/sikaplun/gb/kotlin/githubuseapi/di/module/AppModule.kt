package com.sikaplun.gb.kotlin.githubuseapi.di.module

import com.sikaplun.gb.kotlin.githubuseapi.ui.detail.DetailUserActivityViewModel
import com.sikaplun.gb.kotlin.githubuseapi.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel{
        MainViewModel(
            repos = get()
        )
    }

    viewModel{
        DetailUserActivityViewModel(
            userDetail = get()
        )
    }



}