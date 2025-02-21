package com.app.news.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.app.news.data.network.Network
import com.app.news.ui.common.ArticleListScreen
import com.app.news.ui.common.EmptyContainer
import com.app.news.ui.common.ShimmerEffect
import com.app.news.ui.search.components.SearchBar
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.browser
import newsapp.composeapp.generated.resources.network
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Search(navController: NavController) {
    var search by rememberSaveable{ mutableStateOf("") }
    val searchViewModel= koinViewModel<SearchViewModel>()
    val state by searchViewModel.news.collectAsState()
    Column {
        SearchBar(
            value = search,
            onValueChange = {
                search = it
            },
            onSearch = {
                if (it.trim().isNotEmpty()) {
                    searchViewModel.searchQuery(it)
                }
            }
        )

        state.DisplayResult(
            onIdle = {
              EmptyContainer("Type to Search!",Res.drawable.browser, isVisible = false)
            },
            onLoading = {
                ShimmerEffect()
            },
            onSuccess = {
                if(it.isEmpty()){
                  EmptyContainer("No News",Res.drawable.browser, isVisible = false)
                }else{
                    ArticleListScreen(it,navController)

                }


            },
            onError = {
             EmptyContainer(it,Res.drawable.network, isVisible = true, buttonClicked = {
                 if (it.trim().isNotEmpty()) {
                     searchViewModel.searchQuery(it)
                 }
             })
            }
        )

    }
}