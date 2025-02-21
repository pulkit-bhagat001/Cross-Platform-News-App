package com.app.news.ui.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmptyContainer(text:String,icon:DrawableResource,isVisible:Boolean,buttonClicked:()->Unit={}) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(painter = painterResource(icon), contentDescription = "", modifier = Modifier.size(150.dp),tint=if(isSystemInDarkTheme()) Color.LightGray else Color.DarkGray)
        Text(text, style = MaterialTheme.typography.titleMedium)
        if(isVisible){
            Card(
                onClick = {buttonClicked()},
                shape = RoundedCornerShape(20.dp),
                backgroundColor = Color(0xFFCDBCFD)
            ){
                Text("Retry", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp), color = if(isSystemInDarkTheme()) Color.DarkGray else Color.LightGray)
            }
        }
    }
}