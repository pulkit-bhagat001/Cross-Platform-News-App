package com.app.news.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.news.data.models.Article
import com.app.news.data.repository.LocalNewsRepository
import com.app.news.utils.Resource
import com.app.news.utils.articles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BookmarkViewModel(
    private val localNewsRepository: LocalNewsRepository
):ViewModel(){
    private val _news = MutableStateFlow<Resource<List<Article>>>(Resource.Loading)
    val news=_news.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _news.emit(Resource.Loading)
            try{
               localNewsRepository.getAllArticles().catch {
                   _news.emit(Resource.Error(it.message.toString()))
               }.collect{
                   _news.emit(Resource.Success(it))

               }

            }catch (e:Exception){
                _news.emit(Resource.Error(e.message.toString()))
            }

        }

    }
}