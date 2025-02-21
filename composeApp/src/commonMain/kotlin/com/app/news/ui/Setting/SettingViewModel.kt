package com.app.news.ui.Setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.news.data.repository.LocalNewsRepository
import com.app.news.utils.AppPreferences
import com.app.news.utils.Resource
import com.app.news.utils.articles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingViewModel(
    private val appPreferences: AppPreferences,
    private val localNewsRepository: LocalNewsRepository
):ViewModel(){

    private val _currentTheme= MutableStateFlow<String?>(null)
    val currentTheme=_currentTheme.asStateFlow()

    init {
        currentThemeGet()
    }
    private fun currentThemeGet()= runBlocking {
        _currentTheme.update {
            appPreferences.getTheme()
        }
    }
    fun changeThemeMode(value:String){
        viewModelScope.launch(Dispatchers.IO) {
            appPreferences.changeTheme(value)
            _currentTheme.update {
                value
            }

        }
    }
    fun deleteAllItems(){
        viewModelScope.launch(Dispatchers.IO) {
            localNewsRepository.deleteAllItems()
        }
    }

}