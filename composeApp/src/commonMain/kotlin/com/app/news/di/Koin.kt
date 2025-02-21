package com.app.news.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration={})=
    startKoin {
    appDeclaration()
        modules(
            networkModule,
            databaseModule,
            repositoryModule,
            vmModule
        )

}