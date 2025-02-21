package com.app.news.di

import com.app.news.ui.Setting.SettingViewModel
import com.app.news.ui.articleDetails.CompleteDetailsViewModel
import com.app.news.ui.bookmark.BookmarkViewModel
import com.app.news.ui.headlines.HeadlineViewModel
import com.app.news.ui.search.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val vmModule= module {
    viewModelOf(::HeadlineViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::BookmarkViewModel)
    viewModelOf(::SettingViewModel)
    viewModelOf(::CompleteDetailsViewModel)
}