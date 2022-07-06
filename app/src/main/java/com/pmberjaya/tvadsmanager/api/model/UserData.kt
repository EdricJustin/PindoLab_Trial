package com.pmberjaya.tvadsmanager.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    var id: Int? = null,
    var name: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var is_active: Int? = -1,
    var is_verified: Int? = -1,
    var is_owner: Int? = -1,
    var projects_id: Int? = 0,
    var created_at: String? = null,
    var updated_at: String? = null,
    var token_type: String? = null,
    var expires_in: Int? = 0,
    var access_token: String? = null,
    var cover_path: String? = null
) : Parcelable