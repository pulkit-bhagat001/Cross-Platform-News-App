package com.app.news.ui.articleDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Icon

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import com.app.news.data.models.Article
import com.app.news.data.models.Source
import com.app.news.utils.bool
import com.app.news.utils.shareLink
import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.bookmark
import newsapp.composeapp.generated.resources.bookmark_filled
import newsapp.composeapp.generated.resources.browser
import newsapp.composeapp.generated.resources.compose_multiplatform
import newsapp.composeapp.generated.resources.news_detail
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteDetails(
    author: String?,
    content: String?,
    description: String?,
    publishedAt: String,
    sourceId: String?,
    sourceName: String,
    title: String,
    url: String,
    urlToImage: String?,
    navController: NavHostController
) {
    val completeDetailsViewModel = koinViewModel<CompleteDetailsViewModel>()
    LaunchedEffect(Unit) {
        completeDetailsViewModel.checkBookmark(publishedAt)
    }

    val uriHandler = LocalUriHandler.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(Res.string.news_detail),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = { shareLink(url) }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "",
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    IconButton(onClick = { uriHandler.openUri(url) }) {
                        Icon(
                            painter = painterResource(Res.drawable.browser),
                            contentDescription = "",
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    IconButton(onClick = {
                        completeDetailsViewModel.upsertAndDelete(
                            Article(
                                author, content, description, publishedAt,
                                Source(sourceId, sourceName), title, url, urlToImage
                            )
                        )
                    }) {
                        Icon(
                            painter = painterResource(if (completeDetailsViewModel.bookmark) Res.drawable.bookmark_filled else Res.drawable.bookmark),
                            contentDescription = "",
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            )
        }
    ) {

        LazyColumn(modifier = Modifier.padding(it).padding(horizontal = 10.dp)) {
            item { Spacer(modifier = Modifier.height(20.dp)) }

            if (urlToImage != null) {

                item {
                    SubcomposeAsyncImage(

                        model = urlToImage,
                        contentDescription = "",
                        modifier = if (bool()) Modifier.fillMaxWidth().height(450.dp)
                            .clip(RoundedCornerShape(20.dp)) else Modifier.fillMaxWidth()
                            .height(200.dp).clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.FillBounds,
                        loading = {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(200.dp).align(
                                        Alignment.Center
                                    )
                                )
                            }
                        },
                        error = {
                            Text(
                                text = "Unable to load image",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )
                        },


                        )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            } else {
                item {
                    Image(

                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = "",
                        modifier = if (bool()) Modifier.fillMaxWidth().height(450.dp)
                            .clip(RoundedCornerShape(20.dp)) else Modifier.fillMaxWidth()
                            .height(200.dp).clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop,


                        )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }


            item {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            description?.let {
                item {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            item {
                Text(
                    text = publishedAt,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

        }
    }


}