package com.app.news.ui.Navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object Headlines

    @Serializable
    object Bookmark

    @Serializable
    object Search

    @Serializable
    object Settings

    @Serializable
    data class CompleteDetails(
        val author: String?,
        val content: String?,
        val description: String?,
        val publishedAt: String,
        val sourceId: String?,
        val sourceName: String,
        val title: String,
        val url: String,
        val urlToImage: String?
    )
}