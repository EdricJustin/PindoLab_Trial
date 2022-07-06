package com.pmberjaya.tvadsmanager.cache.model

import androidx.annotation.Nullable
import androidx.lifecycle.MutableLiveData
import com.pmberjaya.tvadsmanager.api.BaseDataResponse
import com.pmberjaya.tvadsmanager.api.BaseResponse
import pmb.attendance.app.api.model.BaseListResponse
import retrofit2.Response
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
class NetworkResource{
    fun <T>processList(liveData: MutableLiveData<Resource<List<T>>>, responseList : Response<BaseListResponse<T>>?, throwable : Throwable?){
        if (responseList!=null){
            when (responseList.code()) {
                200 -> {
                    if (responseList.body()!!.statusCode){
                        liveData.postValue(
                            Resource(
                                Status.SUCCESS,
                                responseList.body()?.data,
                                null
                            )
                        )
                    }else{
                        liveData.postValue(
                            Resource(
                                Status.ERROR,
                                null,
                                responseList.body()?.message?.get(0)
                            )
                        )
                    }
                }
                401 -> {
                    liveData.postValue(
                            Resource(
                                    Status.UNAUTHORIZED,
                                    null,
                                    null
                            )
                    )

                }
                else -> {
                    //internal server error
                    liveData.postValue(
                        Resource(
                            Status.ERROR,
                            null,
                            "Please try again"
                        )
                    )
                }
            }
        }else{
            //handle on failure
            if (throwable is UnknownHostException || throwable is TimeoutException){
                liveData.postValue(
                    Resource(
                        Status.ERROR,
                        null,
                        "Koneksi tidak stabil"
                    )
                )
            }else{
                liveData.postValue(
                    Resource(
                        Status.ERROR,
                        null,
                        throwable.toString()
                    )
                )
            }
        }

    }
    fun <T>processData(liveData: MutableLiveData<Resource<T>>, responseData : Response<BaseDataResponse<T>>?,@Nullable throwable : Throwable?){
        if (responseData!=null){
            when (responseData.code()) {
                200 -> {
                    if (responseData.body()!!.statusCode){
                        liveData.postValue(
                            Resource(
                                Status.SUCCESS,
                                responseData.body()?.data,
                                null
                            )
                        )
                    }else{
                        liveData.postValue(
                            Resource(
                                Status.ERROR,
                                null,
                                responseData.body()?.message?.get(0)
                            )
                        )
                    }
                }
                401 -> {
                    liveData.postValue(
                            Resource(
                                    Status.UNAUTHORIZED,
                                    null,
                                    null
                            )
                    )

                }
                else -> {
                    //internal server error
                    liveData.postValue(
                        Resource(
                            Status.ERROR,
                            null,
                            "Please try again"
                        )
                    )
                }
            }
        }else{
            //handle on failure
            if (throwable is UnknownHostException || throwable is TimeoutException){
                liveData.postValue(
                    Resource(
                        Status.ERROR,
                        null,
                        "Koneksi tidak stabil"
                    )
                )
            }else{
                liveData.postValue(
                    Resource(
                        Status.ERROR,
                        null,
                        throwable.toString()
                    )
                )
            }
        }
    }

    fun processBaseResponse(liveData: MutableLiveData<Resource<BaseResponse>>, responseData : Response<BaseResponse>?,@Nullable throwable : Throwable?){
        if (responseData!=null){
            when (responseData.code()) {
                200 -> {
                    if (responseData.body()!!.statusCode){
                        liveData.postValue(
                            Resource(
                                Status.SUCCESS,
                                responseData.body(),
                                null
                            )
                        )
                    }else{
                        liveData.postValue(
                            Resource(
                                Status.ERROR,
                                null,
                                responseData.body()?.message?.get(0)
                            )
                        )
                    }
                }
                401 -> {
                    liveData.postValue(
                            Resource(
                                    Status.UNAUTHORIZED,
                                    null,
                                    null
                            )
                    )

                }
                else -> {
                    //internal server error
                    liveData.postValue(
                        Resource(
                            Status.ERROR,
                            null,
                            "Please try again"
                        )
                    )
                }
            }
        }else{
            //handle on failure
            if (throwable is UnknownHostException || throwable is TimeoutException){
                liveData.postValue(
                    Resource(
                        Status.ERROR,
                        null,
                        "Koneksi tidak stabil"
                    )
                )
            }else{
                liveData.postValue(
                    Resource(
                        Status.ERROR,
                        null,
                        throwable.toString()
                    )
                )
            }
        }
    }
}
