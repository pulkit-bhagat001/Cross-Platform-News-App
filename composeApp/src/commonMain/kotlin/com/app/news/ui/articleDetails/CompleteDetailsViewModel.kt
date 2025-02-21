package com.app.news.ui.articleDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.news.data.models.Article
import com.app.news.data.repository.LocalNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch


class CompleteDetailsViewModel(
    private val localNewsRepository: LocalNewsRepository
) : ViewModel() {
    var bookmark by mutableStateOf(false)

    fun checkBookmark(id: String) {
        viewModelScope.launch(Dispatchers.IO) {

            localNewsRepository.getParticularItem(id)?.let {
                bookmark=true
            }

        }
    }
    fun upsertAndDelete(article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            if(bookmark){
                localNewsRepository.deleteParticularItem(article)
            }else{
                localNewsRepository.insertArticle(article)
            }
            bookmark=!bookmark
        }
    }

}