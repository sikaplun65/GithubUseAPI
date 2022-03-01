package com.sikaplun.gb.kotlin.githubuseapi.di.component

import com.sikaplun.gb.kotlin.githubuseapi.di.module.AppModule
import com.sikaplun.gb.kotlin.githubuseapi.di.module.DataModule
import com.sikaplun.gb.kotlin.githubuseapi.di.module.RetrofitModule
import com.sikaplun.gb.kotlin.githubuseapi.ui.detail.DetailUserActivity
import com.sikaplun.gb.kotlin.githubuseapi.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, RetrofitModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(detailUserActivity: DetailUserActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance retrofitModule: RetrofitModule): AppComponent
    }
}