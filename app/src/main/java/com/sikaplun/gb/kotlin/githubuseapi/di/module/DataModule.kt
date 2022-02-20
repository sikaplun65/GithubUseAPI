package com.sikaplun.gb.kotlin.githubuseapi.di.module

import com.sikaplun.gb.kotlin.githubuseapi.data.repositories.DetailUserRepositoryRequest
import com.sikaplun.gb.kotlin.githubuseapi.data.repositories.GitRepositoryRequest
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideRepos(): GitRepositoryRequest =
        GitRepositoryRequest(retrofit = RetrofitModule)

    @Provides
    fun provideUserDetail(): DetailUserRepositoryRequest =
        DetailUserRepositoryRequest(retrofit = RetrofitModule)

}