package com.pmberjaya.tvadsmanager.api.network

import androidx.lifecycle.MutableLiveData
import com.pmberjaya.tvadsmanager.api.network.model.*
import com.pmberjaya.tvadsmanager.cache.model.NetworkResource
import com.pmberjaya.tvadsmanager.cache.model.Resource
import com.pmberjaya.tvadsmanager.cache.model.Status
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val networkResource : NetworkResource
) {
    suspend fun getBanner(
        bannerLiveData: MutableLiveData<Resource<List<Banner>>>
    ) {
        bannerLiveData.postValue(Resource(Status.LOADING, null, ""))
        try {
            networkResource.processList(bannerLiveData, apiService.getBanner(), null)
        }catch (e : Exception){
            networkResource.processList(bannerLiveData, null, e)
        }
    }

    suspend fun getNews(
        param: HashMap<String, String>,
        newsLiveData: MutableLiveData<Resource<List<News>>>
    ){
        newsLiveData.postValue(Resource(Status.LOADING, null, ""))
        try {
            networkResource.processList(newsLiveData, apiService.getNews(param), null)
        }catch (e : Exception){
            networkResource.processList(newsLiveData, null, e)
        }
    }

    suspend fun getPaket(
        paketLiveData: MutableLiveData<Resource<List<LayananPaket>>>
    ){
        paketLiveData.postValue(Resource(Status.LOADING, null, ""))
        try {
            var param = HashMap<String,String>()
            param["limit"] = "5"
            networkResource.processList(paketLiveData, apiService.getPaket(param), null)
        }catch (e : Exception){
            networkResource.processList(paketLiveData, null, e)
        }
    }

    suspend fun getLayananKategori(
        param : HashMap<String, String>,
        layanankategoriLiveData: MutableLiveData<Resource<List<LayananKategori>>>
    ){
        layanankategoriLiveData.postValue(Resource(Status.LOADING, null, ""))
        try {
            networkResource.processList(layanankategoriLiveData, apiService.getLayanan(param), null)
        }catch (e : Exception){
            networkResource.processList(layanankategoriLiveData, null, e)
        }
    }

    suspend fun getLayananService(
        param : HashMap<String, String>,
        layananserviceLiveData: MutableLiveData<Resource<List<LayananPaket>>>
    ){
        layananserviceLiveData.postValue(Resource(Status.LOADING, null, ""))
        try {
            networkResource.processList(layananserviceLiveData, apiService.getLayananPaket(param), null)
        }catch (e : Exception){
            networkResource.processList(layananserviceLiveData, null, e)
        }
    }

    suspend fun getPaketDetail(
        packageId : String,
        paketdetailLiveData: MutableLiveData<Resource<LayananPaket>>
    ){
        paketdetailLiveData.postValue(Resource(Status.LOADING, null, ""))
        try{
            networkResource.processData(paketdetailLiveData, apiService.getPaket(packageId), null)
        }catch (e : Exception){
            networkResource.processData(paketdetailLiveData, null, e)
        }
    }
}