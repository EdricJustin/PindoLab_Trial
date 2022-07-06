package com.pmberjaya.tvadsmanager.cache.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginData(

        @field:SerializedName("is_active")
	val isActive: Int? = null,

        @field:SerializedName("is_owner")
	val isOwner: Int? = null,

        @field:SerializedName("cover_path")
	val coverPath: String? = null,

        @field:SerializedName("projects_id")
	val projectsId: Int? = null,

        @field:SerializedName("created_at")
	val createdAt: String? = null,

        @field:SerializedName("token_type")
	val tokenType: String? = null,

        @field:SerializedName("is_verified")
	val isVerified: Int? = null,

        @field:SerializedName("token")
	val token: String? = null,

        @field:SerializedName("access_token")
	val accessToken: String? = null,

        @field:SerializedName("refresh_token")
	val refreshToken: String? = null,

        @field:SerializedName("updated_at")
	val updatedAt: String? = null,

        @field:SerializedName("phone")
	val phone: String? = null,

        @field:SerializedName("name")
	val name: String? = null,

        @field:SerializedName("id")
	val id: Int? = null,

        @field:SerializedName("expires_in")
	val expiresIn: Int? = null,

        @field:SerializedName("email")
	val email: String? = null
) : Parcelable
