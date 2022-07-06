package com.pmberjaya.tvadsmanager.api.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LayananKategori(
    var id: String ?= null,
    var service_categories_id: String ?= null,
    var name: String ?= null,
    var image_path: String ?= null,
    var service: List<LayananPaket> = ArrayList<LayananPaket>()
) : Parcelable

@Parcelize
data class LayananPaket(
    var id: String ?= null,
    var service_categories_id: String ?= null,
    var name: String ?= null,
    var description: String ?= null,
    var cover_image_link: String ?= null,
    var price: String ?= null,
    var type: String ?= null,
    var benefit: String ?= null,
    var preparation: String ?= null,
    var package_link : List<LayananLinkPaket> = ArrayList<LayananLinkPaket>()
) : Parcelable

@Parcelize
data class LayananLinkPaket(
    var id: String ?= null,
    var packages_id: String ?= null,
    var services_id: String ?= null,
    var service: LayananPaket ?= null
) : Parcelable
