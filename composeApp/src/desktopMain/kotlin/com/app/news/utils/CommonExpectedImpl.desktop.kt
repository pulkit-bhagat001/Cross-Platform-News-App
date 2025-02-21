package com.app.news.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.news.data.database.NewsDatabase
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File
import java.util.UUID

actual fun getName(): String {
    return "Windows"
}

actual fun bool(): Boolean {
    return true
}

actual fun getUniqueId(): String {
    return UUID.randomUUID().toString()
}

actual fun shareLink(url: String) {
    val clipboard=Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url),null)
}

actual fun datastorePreferences(): DataStore<Preferences> {
    return AppSettings.getDataStore(
        producerPath = {
            DATASTORE_FILE_NAME
        }
    )
}

actual fun getRoomDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val path=File(System.getProperty("java.io.tmpdir"), DB_NAME)
    return Room.databaseBuilder(path.absolutePath)
}