package com.app.news.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.news.data.models.Article
import com.app.news.data.models.ErrorResponse
import com.app.news.data.models.NewsResponse
import com.app.news.data.network.Network
import com.app.news.utils.Resource
import com.app.news.utils.articles
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class SearchViewModel(
    private val network: Network
):ViewModel(){
    private val _news = MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val news=_news.asStateFlow()

    fun searchQuery(query:String) {
        viewModelScope.launch(Dispatchers.IO) {
            _news.emit(Resource.Loading)
            try{
                val response= network.getBySearch(query)
                if(response.status.value in 200..299){
                    val body=response.body<NewsResponse>()
                    _news.emit(Resource.Success(body.articles))
                }else{
                    val body=response.body<ErrorResponse>()
                    _news.emit(Resource.Error(body.message))
                }

            }catch (e:Exception){
                _news.emit(Resource.Error(e.message.toString()))
            }

        }

    }
}