package com.sikaplun.gb.kotlin.githubuseapi.app

import android.app.Application
import com.sikaplun.gb.kotlin.githubuseapi.di.module.appModule
import com.sikaplun.gb.kotlin.githubuseapi.di.module.dataModule
import com.sikaplun.gb.kotlin.githubuseapi.di.module.retrofitModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            modules(listOf(appModule, dataModule, retrofitModule))
        }
    }
}
