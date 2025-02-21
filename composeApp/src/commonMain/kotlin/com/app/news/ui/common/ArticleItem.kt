package com.app.news.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import com.app.news.data.models.Article

import newsapp.composeapp.generated.resources.Res
import newsapp.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource


@Composable
fun ArticleItem(
    article: Article,
    onClick: () -> Unit

) {
    Row(modifier = Modifier.fillMaxWidth().height(120.dp).clickable {
        onClick()
    }, verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f).fillMaxSize()) {
            Card(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(20.dp)) {
                if (article.urlToImage != null) {
                    SubcomposeAsyncImage(
                        model = article.urlToImage,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop,
                    loading = { Box(modifier = Modifier.fillMaxSize()){
                        CircularProgressIndicator(modifier = Modifier.size(100.dp).align(
                            Alignment.Center))
                    }
                    },
                    error = { Text(text = "Unable to load image",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold) },



                    )

                }else{

                   Image(
                        painter = painterResource(Res.drawable.compose_multiplatform),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.FillBounds,




                        )
                }
            }

        }
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(2f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
            Text(text = article.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2)
            article.description?.let {
                Text(text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2)
            }
            Text(text = article.source.name,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold)
        }

    }
}