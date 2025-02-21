package com.app.news.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AppPreferences(
    private val dataStore: DataStore<Preferences>
) {
    private val key= stringPreferencesKey("theme")

    suspend fun getTheme()=dataStore.data.map {
        it[key]?:Theme.DARK_MODE.name
    }.first()

    suspend fun changeTheme(theme:String){
        dataStore.edit {
            it[key]=theme
        }
    }

}