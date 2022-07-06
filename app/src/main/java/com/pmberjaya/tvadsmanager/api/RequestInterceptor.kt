package com.pmberjaya.tvadsmanager.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response


class RequestInterceptor(private val requestHeaders: RequestHeaders) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder =
            original.newBuilder().header("Authorization", "Bearer " + requestHeaders.accesstoken.accessToken)
                .header("Accept", requestHeaders.language)
                .method(original.method, original.body)
        val newRequest = builder.build()
        Log.d("token", requestHeaders.accesstoken.accessToken)

        return chain.proceed(newRequest)
    }
}