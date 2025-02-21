package com.app.news.data.database

import androidx.room.TypeConverter
import com.app.news.data.models.Source
import kotlinx.serialization.json.Json

class SourceTypeConverter {

    @TypeConverter
    fun getSourceFromString(value:String):Source{
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun getStringFromSource(value:Source):String{
        return Json.encodeToString(value)
    }
}