package com.app.news

import android.app.Application
import com.app.news.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class BaseApp:Application(),KoinComponent{
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@BaseApp)
        }
    }
}