package com.app.news

import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.app.news.di.initKoin
import java.awt.Dimension

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "NewsApp",
       state = WindowState(position = WindowPosition(Alignment.Center))
    ) {//window.minimumSize= Dimension(1280,760)
        App()
    }
}