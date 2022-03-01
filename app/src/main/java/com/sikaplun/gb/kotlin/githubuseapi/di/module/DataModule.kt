package com.sikaplun.gb.kotlin.githubuseapi.di.module

import com.sikaplun.gb.kotlin.githubuseapi.data.repositories.DetailUserRepositoryRequest
import com.sikaplun.gb.kotlin.githubuseapi.data.repositories.GitRepositoryRequest
import org.koin.dsl.module

val dataModule = module {
    single { GitRepositoryRequest(apiGitHub = get()) }
    single { DetailUserRepositoryRequest(apiGitHub = get()) }
}