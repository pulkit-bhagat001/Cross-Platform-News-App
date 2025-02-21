package com.app.news.data.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "articleTable")
data class Article(
    @SerialName("author")
    val author: String?,
    @SerialName("content")
    val content: String?,
    @SerialName("description")
    val description: String?,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "articleId")
    @SerialName("publishedAt")
    val publishedAt: String,
    @SerialName("source")
    val source: Source,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String,
    @SerialName("urlToImage")
    val urlToImage: String?
)