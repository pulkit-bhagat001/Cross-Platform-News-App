package com.app.news.ui.bookmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.app.news.ui.common.ArticleListScreen
import com.app.news.ui.common.EmptyContainer
import com.app.news.ui.common.ShimmerEffect
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.browser
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Bookmark(navController: NavController) {
    val bookmarkViewModel= koinViewModel<BookmarkViewModel>()
    val state by bookmarkViewModel.news.collectAsState()
    state.DisplayResult(
        onIdle = {},
        onLoading = {
            ShimmerEffect()
        },
        onSuccess = {
            if(it.isEmpty()){
                EmptyContainer("No Bookmarks", Res.drawable.browser, isVisible = false)
            }else{
              ArticleListScreen(it,navController)

            }


        },
        onError = {
            EmptyContainer(it,Res.drawable.browser, isVisible = false)
        }
    )
}