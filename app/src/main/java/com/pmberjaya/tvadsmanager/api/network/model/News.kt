package com.pmberjaya.tvadsmanager.api.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News (
    var cover_image: String ?= null,
    var title: String ?= null,
    var description: String ?= null,
    var link: String ?= null
) : Parcelable
