package com.pmberjaya.tvadsmanager.ui.layanan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmberjaya.tvadsmanager.api.network.Repository
import com.pmberjaya.tvadsmanager.api.network.model.LayananKategori
import com.pmberjaya.tvadsmanager.api.network.model.LayananPaket
import com.pmberjaya.tvadsmanager.cache.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LayananViewModel @Inject constructor(
    var repository: Repository
) :
    ViewModel(){
    var layanankategoriLiveData = MutableLiveData<Resource<List<LayananKategori>>>()
    fun getLayananKategoriData(){
        viewModelScope.launch {
            var param = HashMap<String,String>()
            repository.getLayananKategori(param, layanankategoriLiveData)
        }
    }

    var layananserviceLiveData = MutableLiveData<Resource<List<LayananPaket>>>()
    fun getLayananServiceData(categoryId: String, type: String){
        viewModelScope.launch {
            var param = HashMap<String,String>()
            if(type == "1"){
                param["type"] = type
                param["category"] = categoryId
            }else if(type == "2"){
                param["type"] = type
            }
            repository.getLayananService(param, layananserviceLiveData)
        }
    }

    var paketdetailLiveData = MutableLiveData<Resource<LayananPaket>>()
    fun getPaketDetailData(packageId : String){
        viewModelScope.launch {
            repository.getPaketDetail(packageId, paketdetailLiveData)
        }
    }
}