package com.app.news.ui.headlines

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.app.news.data.network.Network
import com.app.news.ui.Navigation.Routes
import com.app.news.ui.common.ArticleListScreen
import com.app.news.ui.common.EmptyContainer
import com.app.news.ui.common.ShimmerEffect
import com.app.news.utils.Resource
import com.app.news.utils.categoryList
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.browser
import newsapp.composeapp.generated.resources.network
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Headlines(navController: NavController) {
    val backStackEntry = navController.getBackStackEntry(Routes.Headlines::class.qualifiedName!!)
    val headlineViewModel= koinViewModel<HeadlineViewModel>(viewModelStoreOwner = backStackEntry)

    val state by headlineViewModel.news.collectAsState()
    Column {
            Row(modifier = Modifier.fillMaxWidth().padding(15.dp).horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                categoryList.fastForEachIndexed { i, s ->
                    FilterChip(onClick = {
                        headlineViewModel.getNews(s)
                    }, selected = headlineViewModel.categoryName==s,
                        label = { Text(s, color = MaterialTheme.colorScheme.onBackground) })
                }
            }

         state.DisplayResult(
            onIdle = {},
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = {

                if(it.isEmpty()){
                    EmptyContainer("No News", Res.drawable.browser, isVisible = false)
                }else{
                    ArticleListScreen(it,navController)

                }


            },
            onError = {
                EmptyContainer(it,Res.drawable.network, isVisible = true, buttonClicked = {
                    headlineViewModel.getNews(categoryList[0])
                })
            }
        )}
    }


