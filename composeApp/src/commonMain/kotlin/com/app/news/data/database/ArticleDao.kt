package com.app.news.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.app.news.data.models.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Upsert
    suspend fun insertArticle(value:Article)

    @Query("Select * from articleTable")
    fun getAllArticles():Flow<List<Article>>

    @Query("Delete from articleTable")
    suspend fun deleteAllItems()

    @Delete
    suspend fun deleteParticularItem(value:Article)

    @Query("select * from articleTable where articleId =:id")
    suspend fun getParticularItem(id:String):Article?
}