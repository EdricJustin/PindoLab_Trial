package com.pmberjaya.tvadsmanager.api

import com.pmberjaya.tvadsmanager.api.model.AccessToken

data class RequestHeaders (
        var accesstoken : AccessToken,
        val language :String
)