package com.app.news.ui.headlines

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.news.data.models.Article
import com.app.news.data.models.ErrorResponse
import com.app.news.data.models.NewsResponse
import com.app.news.data.network.Network
import com.app.news.utils.Resource
import com.app.news.utils.articles
import com.app.news.utils.categoryList
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.util.network.UnresolvedAddressException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeadlineViewModel(
   private val network: Network
):ViewModel() {
    private val _news = MutableStateFlow<Resource<List<Article>>>(Resource.Loading)
    val news = _news.asStateFlow()

    var categoryName by mutableStateOf(categoryList[0])
    var isDataLoaded by mutableStateOf<Boolean?>(null)

    init {
        getNews(categoryName)
        println("Fetched getNews")

    }

    fun getNews(category: String) {

        if (isDataLoaded == null||category!=categoryName) {
            categoryName = category
            viewModelScope.launch(Dispatchers.IO) {
                _news.emit(Resource.Loading)
                try {
                    val newsResponse = network.getHeadlines(category)
                    if (newsResponse.status.value in 200..299) {
                        val body = newsResponse.body<NewsResponse>()
                        _news.emit(Resource.Success(body.articles))
                    } else {
                        val error = newsResponse.body<ErrorResponse>()
                        _news.emit(Resource.Error(error.message))
                    }


                } catch (e: IOException) {
                    _news.emit(Resource.Error("Network error. Please check your connection."))
                } catch (e: ConnectTimeoutException) {
                    _news.emit(Resource.Error("Connection timed out. Please try again."))
                } catch (e: ResponseException) {
                    _news.emit(Resource.Error("Server error: ${e.response.status.value}"))
                } catch (e: UnresolvedAddressException) {
                    _news.emit(Resource.Error("Unable to resolve host. Check your internet."))
                } catch (e: Exception) {
                    _news.emit(Resource.Error(e.toString()))
                }

            }

        }
        isDataLoaded=true
    }
    fun refresh() {

            viewModelScope.launch(Dispatchers.IO) {
                _news.emit(Resource.Loading)
                try {
                    val newsResponse = network.getHeadlines(categoryName)
                    if (newsResponse.status.value in 200..299) {
                        val body = newsResponse.body<NewsResponse>()
                        _news.emit(Resource.Success(body.articles))
                    } else {
                        val error = newsResponse.body<ErrorResponse>()
                        _news.emit(Resource.Error(error.message))
                    }


                } catch (e: IOException) {
                    _news.emit(Resource.Error("Network error. Please check your connection."))
                } catch (e: ConnectTimeoutException) {
                    _news.emit(Resource.Error("Connection timed out. Please try again."))
                } catch (e: ResponseException) {
                    _news.emit(Resource.Error("Server error: ${e.response.status.value}"))
                } catch (e: UnresolvedAddressException) {
                    _news.emit(Resource.Error("Unable to resolve host. Check your internet."))
                } catch (e: Exception) {
                    _news.emit(Resource.Error(e.toString()))
                }

            }

        }
    }
