package com.app.news.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.news.data.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsDatabase:RoomDatabase(),DB{
    abstract fun getDao():ArticleDao
    override fun clearAllTable() {
        super.clearAllTable()
    }
}
interface DB{
    fun clearAllTable():Unit{}
}