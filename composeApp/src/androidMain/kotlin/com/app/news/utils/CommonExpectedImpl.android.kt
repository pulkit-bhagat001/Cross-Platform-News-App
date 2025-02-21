package com.app.news.utils

import android.app.Activity
import android.content.Intent
import android.provider.Telephony.Mms.Intents
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.news.data.database.NewsDatabase
import java.util.UUID

actual fun getName(): String {
    return "Android"
}

actual fun bool(): Boolean {
    return false
}

actual fun getUniqueId(): String {
return UUID.randomUUID().toString()
}

actual fun shareLink(url: String) {
    val intent= Intent().apply {
        action=Intent.ACTION_SEND
        type="text/plain"
        putExtra(Intent.EXTRA_TEXT,url)
    }
    val intentChooser=Intent.createChooser(intent,"Share Using")
    provideActivity().startActivity(intentChooser)
}
var provideActivity:()->Activity={
    throw IllegalArgumentException("Error")
}
fun activityProvider( onProvide: ()->Activity ){
    provideActivity=onProvide
}

actual fun datastorePreferences(): DataStore<Preferences> {
    return AppSettings.getDataStore(
        producerPath = {
            provideActivity().filesDir.resolve(DATASTORE_FILE_NAME).absolutePath
        }
    )
}

actual fun getRoomDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val activity=provideActivity.invoke()
    val path= activity.getDatabasePath(DB_NAME)
    return Room.databaseBuilder<NewsDatabase>(
        activity,
        path.absolutePath
    )
}