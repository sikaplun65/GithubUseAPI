package com.sikaplun.gb.kotlin.githubuseapi.di.module

import com.sikaplun.gb.kotlin.githubuseapi.data.repositories.DetailUserRepositoryRequest
import com.sikaplun.gb.kotlin.githubuseapi.data.repositories.GitRepositoryRequest
import com.sikaplun.gb.kotlin.githubuseapi.ui.detail.DetailUserActivityModelFactory
import com.sikaplun.gb.kotlin.githubuseapi.ui.main.MainViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideMainViewModelFactory(
        repos: GitRepositoryRequest
    ): MainViewModelFactory = MainViewModelFactory(repos)


    @Singleton
    @Provides
    fun provideDetailUserActivityViewModelFactory(
        userDetail: DetailUserRepositoryRequest
    ): DetailUserActivityModelFactory = DetailUserActivityModelFactory(userDetail)

}