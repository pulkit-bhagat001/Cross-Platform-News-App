package com.app.news.di

import com.app.news.utils.AppPreferences
import com.app.news.utils.datastorePreferences
import com.app.news.utils.getNewsDatabaseInstance
import com.app.news.utils.getRoomDatabaseBuilder
import org.koin.dsl.module

val databaseModule= module {

    single {
        getNewsDatabaseInstance(getRoomDatabaseBuilder())
    }
    single {
        getNewsDatabaseInstance(getRoomDatabaseBuilder()).getDao()
    }
    single{
        AppPreferences(datastorePreferences())
    }
}