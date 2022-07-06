package com.pmberjaya.tvadsmanager.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmberjaya.tvadsmanager.api.network.Repository
import com.pmberjaya.tvadsmanager.api.network.model.News
import com.pmberjaya.tvadsmanager.cache.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    var repository: Repository
) :
    ViewModel(){
    var allnewsLiveData = MutableLiveData<Resource<List<News>>>()
    fun getAllNewsData(){
        viewModelScope.launch {
            var param = HashMap<String,String>()
            repository.getNews(param, allnewsLiveData)
        }
    }
}