package com.pmberjaya.tvadsmanager.ui.beranda

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmberjaya.tvadsmanager.api.network.model.Banner
import com.pmberjaya.tvadsmanager.api.network.model.News
import com.pmberjaya.tvadsmanager.api.network.Repository
import com.pmberjaya.tvadsmanager.api.network.model.LayananPaket
import com.pmberjaya.tvadsmanager.cache.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BerandaViewModel @Inject constructor(
    var repository: Repository
) :
    ViewModel(){
    var bannerLiveData = MutableLiveData<Resource<List<Banner>>>()
    fun getBannerData() {
        viewModelScope.launch {
            repository.getBanner(bannerLiveData)
        }
    }

    var newsLiveData = MutableLiveData<Resource<List<News>>>()
    fun getNewsData(){
        viewModelScope.launch {
            var param = HashMap<String,String>()
            param["limit"] = "5"
            repository.getNews(param, newsLiveData)
        }
    }

    var paketLiveData = MutableLiveData<Resource<List<LayananPaket>>>()
    fun getPaketData(){
        viewModelScope.launch {
            repository.getPaket(paketLiveData)
        }
    }
}
