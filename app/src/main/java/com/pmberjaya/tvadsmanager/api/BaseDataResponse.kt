package com.pmberjaya.tvadsmanager.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseDataResponse<T>(
    @SerializedName("status")
    @Expose
    val statusCode: Boolean,
    @SerializedName("messages")
    @Expose
    var message: List<String>? = ArrayList<String>(),
    @SerializedName("data")
    @Expose
    val data: T? = null
)