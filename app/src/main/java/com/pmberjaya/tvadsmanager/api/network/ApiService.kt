package com.pmberjaya.tvadsmanager.api.network

import com.pmberjaya.tvadsmanager.api.BaseDataResponse
import com.pmberjaya.tvadsmanager.api.network.model.*
import pmb.attendance.app.api.model.BaseListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {
    companion object {
        const val URL = "https://api-pindo.kotasatelit.com/"
    }

    @GET("api/banner")
    suspend fun getBanner(): Response<BaseListResponse<Banner>>

    @GET("api/news")
    suspend fun getNews(@QueryMap params : HashMap<String,String>): Response<BaseListResponse<News>>

    @GET("api/packages")
    suspend fun getPaket(@QueryMap params : HashMap<String,String>): Response<BaseListResponse<LayananPaket>>

    @GET("api/service-categories")
    suspend fun getLayanan(@QueryMap params : HashMap<String, String>): Response<BaseListResponse<LayananKategori>>

    @GET("api/services")
    suspend fun getLayananPaket(@QueryMap params : HashMap<String, String>): Response<BaseListResponse<LayananPaket>>

    @GET("api/packages/{package_id}")
    suspend fun getPaket(@Path("package_id") packageId : String): Response<BaseDataResponse<LayananPaket>>
}