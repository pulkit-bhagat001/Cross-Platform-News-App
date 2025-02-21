package com.app.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.app.news.ui.MainScreen
import com.app.news.ui.Setting.SettingViewModel
import com.app.news.utils.AppPreferences
import com.app.news.utils.datastorePreferences
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import theme.NewsTheme

@Composable
@Preview
fun App() {
    val settingViewModel= koinViewModel<SettingViewModel>()
    val currentTheme by settingViewModel.currentTheme.collectAsState()
    NewsTheme(currentTheme) {
        MainScreen(settingViewModel,settingViewModel)
    }
}