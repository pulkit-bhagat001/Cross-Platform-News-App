package com.app.news.di

import com.app.news.data.network.Network
import com.app.news.data.repository.LocalNewsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule= module {

    singleOf(::Network)
    singleOf(::LocalNewsRepository)
}