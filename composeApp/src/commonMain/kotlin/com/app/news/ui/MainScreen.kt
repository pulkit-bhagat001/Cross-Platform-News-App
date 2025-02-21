package com.app.news.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.app.news.ui.Navigation.NewsBottomNavBar
import com.app.news.ui.Navigation.Routes
import com.app.news.ui.Setting.Setting
import com.app.news.ui.Setting.SettingViewModel
import com.app.news.ui.bookmark.Bookmark
import com.app.news.ui.articleDetails.CompleteDetails
import com.app.news.ui.headlines.HeadlineViewModel
import com.app.news.ui.headlines.Headlines
import com.app.news.ui.search.Search
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(settingViewModel: SettingViewModel, settingViewModel1: SettingViewModel) {
    var name by rememberSaveable { mutableStateOf<String?>("not") }
    var title by rememberSaveable { mutableStateOf("") }
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route
    var index by rememberSaveable { mutableStateOf(0) }
    LaunchedEffect(currentScreen) {
        index = when (currentScreen) {
            Routes.Headlines::class.qualifiedName -> 0
            Routes.Search::class.qualifiedName -> 1
            Routes.Bookmark::class.qualifiedName -> 2
            else -> index
        }
        title=currentScreen?.substringAfterLast(".")?:"App"


    }
    Scaffold(topBar = {
        if (!name.isNullOrEmpty()) {
            TopAppBar(
                title = {
                    Text(
                        text =title,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Routes.Settings)
                    }) {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = "")
                    }
                }
            )
        }else if(name==""){
            TopAppBar(
                title = {
                    Text(
                        text =title,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()}){
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        }else{

        }
    }, bottomBar = {
        if (!name.isNullOrEmpty()) {
            NewsBottomNavBar(index, onIndex = {
                index = it
                val route=when(it){
                    0->Routes.Headlines
                    1->Routes.Search
                    2->Routes.Bookmark
                    else-> return@NewsBottomNavBar
                }
                if(currentScreen!=route::class.qualifiedName){
                    navController.navigate(route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState=true
                        }
                        launchSingleTop=true
                        restoreState=true
                    }

                }



            })
        }

    }

    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.Headlines,
            modifier = Modifier.padding(it)
        ) {

            composable<Routes.Headlines> {
                name = "not"
                //Headlines(navController,headlineViewModel)
                Headlines(navController)
            }
            composable<Routes.Search> {
                name = "not"
                Search(navController)
            }
            composable<Routes.Bookmark> {
                name = "not"
                Bookmark(navController)
            }
            composable<Routes.Settings> {
                name = ""
                Setting(settingViewModel)
            }
            composable<Routes.CompleteDetails> {it->
                    name = null
                    val data=it.toRoute<Routes.CompleteDetails>()
                    CompleteDetails(data.author,data.content,data.description,data.publishedAt,data.sourceId,data.sourceName,data.title,data.url,data.urlToImage,navController)




            }
        }


    }
}

//when(it){
//    0->{
//        if(currentScreen!=Routes.Headlines::class.qualifiedName){
//            navController.navigate(Routes.Headlines){
//
//                restoreState=true
//            }
//        }
//    }
//    1->{
//        if(currentScreen!=Routes.Search::class.qualifiedName){
//            navController.navigate(Routes.Search)
//        }
//    }
//    2->{
//        if(currentScreen!=Routes.Bookmark::class.qualifiedName){
//            navController.navigate(Routes.Bookmark)
//        }
//    }
//}




//                when (it) {
//                    0 -> {
//                        if (currentScreen != Routes.Headlines::class.qualifiedName) {
//                            navController.navigate(Routes.Headlines){
//
//                                launchSingleTop=true
//                                restoreState=true
//                            }
//                        }
//                    }
//
//                    1 -> {
//                        if (currentScreen != Routes.Search::class.qualifiedName) {
//                            navController.navigate(Routes.Search){
//                                launchSingleTop=true
//                                restoreState=true
//                            }
//                        }
//                    }
//
//                    2 -> {
//                        if (currentScreen != Routes.Bookmark::class.qualifiedName) {
//                            navController.navigate(Routes.Bookmark){
//                                launchSingleTop=true
//                                restoreState=true
//                            }
//                        }
//                    }
//                }