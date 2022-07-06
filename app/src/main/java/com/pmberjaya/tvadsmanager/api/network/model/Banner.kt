package com.pmberjaya.tvadsmanager.api.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Banner (
    val id: Int ?= null,
    var title: String ?= null,
    var description: String ?= null,
    var cover_image_link: String ?= null,
    var date : String ?= null,
    var info: String ?= null
) : Parcelable
