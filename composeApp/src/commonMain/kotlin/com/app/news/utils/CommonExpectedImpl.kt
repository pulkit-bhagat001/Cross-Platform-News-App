package com.app.news.utils

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.app.news.data.database.NewsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import okio.Path.Companion.toPath

expect fun getName(): String
expect fun bool(): Boolean
expect fun getUniqueId(): String
expect fun shareLink(url: String)
expect fun datastorePreferences(): DataStore<Preferences>

object AppSettings {
    private lateinit var dataStore: DataStore<Preferences>

    @OptIn(InternalCoroutinesApi::class)
    private val lock = SynchronizedObject()

    @OptIn(InternalCoroutinesApi::class)
    fun getDataStore(producerPath: () -> String): DataStore<Preferences> {
        return synchronized(lock) {
            if (::dataStore.isInitialized) {
                dataStore
            } else {
                PreferenceDataStoreFactory.createWithPath(
                    produceFile = { producerPath().toPath() }
                ).also { dataStore = it }
            }
        }
    }
}

expect fun getRoomDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase>

fun getNewsDatabaseInstance(
    builder: RoomDatabase.Builder<NewsDatabase>
): NewsDatabase {
    return builder.setDriver(BundledSQLiteDriver()).setQueryCoroutineContext(Dispatchers.IO).build()
}
