package com.app.news.data.repository

import com.app.news.data.database.ArticleDao
import com.app.news.data.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class LocalNewsRepository(
    private val dao:ArticleDao
) {
    suspend fun insertArticle(article: Article){
        dao.insertArticle(article)
    }
   fun getAllArticles(): Flow<List<Article>> {
        return dao.getAllArticles().flowOn(Dispatchers.IO)
    }
    suspend fun deleteAllItems(){
        dao.deleteAllItems()
    }
    suspend fun deleteParticularItem(value:Article){
        dao.deleteParticularItem(value)
    }
    suspend fun getParticularItem(value:String):Article?{
        return dao.getParticularItem(value)
    }

}