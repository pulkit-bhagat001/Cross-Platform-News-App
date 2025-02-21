package com.app.news.ui.Setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.news.ui.Setting.component.DeleteBookmarkDialog
import com.app.news.ui.Setting.component.SettingItem
import com.app.news.ui.Setting.component.ThemeSelectionDialog
import com.app.news.ui.search.SearchViewModel
import com.app.news.utils.Theme
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.delete
import newsapp.composeapp.generated.resources.theme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Setting(
settingViewModel: SettingViewModel
) {
    var showDeleteBookmark by remember { mutableStateOf(false) }
    var showThemeSelection by remember { mutableStateOf(false) }
    val currentTheme by settingViewModel.currentTheme.collectAsState()
    when {
        showThemeSelection -> {
            ThemeSelectionDialog(
                onThemeChanged = {
                    settingViewModel.changeThemeMode(it.name)
                    showThemeSelection = false
                },
                onDismissRequest = { showThemeSelection = false },
                currentTheme = currentTheme ?: Theme.DARK_MODE.name
            )

        }

        showDeleteBookmark -> {
            DeleteBookmarkDialog(
                onDeleteBookMark = {
                    settingViewModel.deleteAllItems()
                    showDeleteBookmark = false
                },
                onDismissRequest = { showDeleteBookmark = false }
            )
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        SettingItem(
            onClick = {
                showThemeSelection = true
            },
            name = stringResource(Res.string.theme),
            imageVector = Icons.Filled.Edit,

            )
        SettingItem(
            onClick = {
                showDeleteBookmark = true
            },
            name = stringResource(Res.string.delete),
            imageVector = Icons.Filled.Delete,
            color = MaterialTheme.colorScheme.error
        )

    }
}