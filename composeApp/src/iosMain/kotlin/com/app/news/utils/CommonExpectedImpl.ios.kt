package com.app.news.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.news.data.database.NewsDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSUUID
import platform.Foundation.NSUserDomainMask
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import data.database.instantiateImpl

actual fun getName(): String {
   return "IOS"
}

actual fun bool(): Boolean {
    return false
}

actual fun getUniqueId(): String {
    return NSUUID().UUIDString()
}

actual fun shareLink(url: String) {
    val currentViewController=UIApplication.sharedApplication().keyWindow?.rootViewController
    val activityViewController=UIActivityViewController(listOf(url),null)
    currentViewController?.presentViewController(
        viewControllerToPresent = activityViewController,
        animated = true,
        completion = null
    )

}

@OptIn(ExperimentalForeignApi::class)
actual fun datastorePreferences(): DataStore<Preferences> {
    return AppSettings.getDataStore(
        producerPath = {
            val documentDirectory=NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )
            requireNotNull(documentDirectory).path()+"/$DATASTORE_FILE_NAME"
        }
    )
}

@OptIn(ExperimentalForeignApi::class)
actual fun getRoomDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val path=NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )
    return Room.databaseBuilder(
        name = requireNotNull(path).path()+"/$DB_NAME",
        factory = {NewsDatabase::class.instantiateImpl()}
    )

}