package com.app.news.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.news.data.models.Article
import com.app.news.ui.Navigation.Routes
import com.app.news.utils.articles
import com.app.news.utils.bool
import com.app.news.utils.getUniqueId
import kotlin.uuid.Uuid

@Composable
fun ArticleListScreen(articles:List<Article>, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(if(bool()) 3 else 1),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(15.dp)
    ){
        items(articles, key = {
            it.publishedAt+ getUniqueId()
        }){
                ArticleItem(it, onClick = {
                    navController.navigate(Routes.CompleteDetails(it.author,it.content,it.description,it.publishedAt,it.source.id,it.source.name,it.title,it.url,it.urlToImage))
                })
        }
    }
}